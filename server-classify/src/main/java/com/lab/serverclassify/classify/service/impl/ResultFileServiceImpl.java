package com.lab.serverclassify.classify.service.impl;

import com.lab.serverclassify.classify.service.ResultFileService;
import com.lab.serverclassify.mapper.VersionFileMapper;
import com.lab.serverclassify.pojo.bo.ResultFileBo;
import com.lab.serverclassify.pojo.domain.VersionFile;
import com.lab.serverclassify.properties.HdfsProperties;
import com.lab.serverclassify.utils.CreateResultFileUtil;
import com.lab.serverclassify.utils.EsSearchUtil;
import com.lab.serverclassify.utils.HbaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @author weixun
 */
@Service
@Slf4j
public class ResultFileServiceImpl implements ResultFileService {


    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Autowired
    private CreateResultFileUtil createResultFileUtil;

    @Autowired
    private HbaseUtil hbaseUtil;

    @Autowired
    private VersionFileMapper versionFileMapper;

    @Autowired
    private HDFSFileSystem hdfsFileSystem;

    @Autowired
    private HdfsProperties hdfsProperties;

    @Autowired
    private EsSearchUtil esSearchUtil;

    private static final long MB = 1024 * 1024;

    /**
     * 生产结果文件, 如果文件大于或等于一个块的大小（128M）则保存至hdfs
     * 否则就保存在本地
     *
     * @param username  用户名
     * @param operation 最新的一次操作
     */
    @Override
    public String generateFile(String username, String operation) {
        Instant t1 = Instant.now();
        int bolckSize = 128;
        String filename = username + "_" + operation;
        VersionFile versionFile = new VersionFile(username, operation, filename, 1);
        String hdfsPath = null;
        List<String> rowkeys = esSearchUtil.getAllRowkeysByUsernameAndVersion(username, operation);
        ResultFileBo resultFileBo = hbaseUtil.findByRowkeys(rowkeys, "goodslabel");
        createResultFileUtil.mppCreateFile(resultFileBo, filename);
        File file = new File(filename);
        if (file.length() >= bolckSize * MB) {
            try {
                FileInputStream in = new FileInputStream(filename);
                hdfsPath = hdfsFileSystem.createFile(in, username, filename, hdfsProperties.getResultPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (hdfsPath != null) {
            versionFile.setFilePath(hdfsPath);
            versionFile.setKind(2);
        }
        versionFileMapper.insert(versionFile);
        Instant t2 = Instant.now();
        log.info("sum time consuming: " + Duration.between(t1, t2).toMillis());
        return filename;
    }

    @Override
    public String getResultFilePath(String username, String operation) {
        VersionFile versionFile = versionFileMapper.getVersionFileByUsername(username, operation);
        if (versionFile != null) {
            String filePath = versionFile.getFilePath();
            if (versionFile.getKind() == 1) {
                return filePath;
            }
            String filename = username + "_" + operation;
            File file = new File(filename);
            if (file.exists()) {
                return filename;
            }
            try {
                hdfsFileSystem.download(filePath, filename);
                return filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return generateFile(username, operation);
    }
}
