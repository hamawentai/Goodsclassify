package com.lab.serverclassify.classify.serviceImpl;


import com.lab.serverclassify.properties.HdfsProperties;
import com.lab.serverclassify.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * @author weixun
 */
@Service
@Slf4j
public class HDFSFileSystem {

    @Autowired
    private HdfsProperties hdfsProperties;


    /**
     * 按路径上传文件到hdfs
     */
    public void copyFile(Configuration conf, String uri, String local, String remote) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        log.info("copy from: " + local + " to " + remote);
        fs.close();
    }

    /**
     * 按路径下载hdfs上的文件
     */
    public void download(Configuration conf, String uri, String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        fs.copyToLocalFile(path, new Path(local));
        log.info("download: from" + remote + " to " + local);
        fs.close();
    }

    /**
     * File对象上传到hdfs
     */
    public String createFile(InputStream in, String username, String filename) throws IOException {
        String[] date = TimeUtils.getNowTime().split("\\|");
        String hdfsPath = "/" + hdfsProperties.getUploadPath() + "/" + username + "/" + date[0] + "/" + filename + "_" + date[1];
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", hdfsProperties.getDefaultFS());
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf);
            FSDataOutputStream out = fileSystem.create(new Path(hdfsPath));
            IOUtils.copyBytes(in, out, 4096, false);
            out.hsync();
            out.close();
            log.info("create file in hdfs:" + hdfsPath);
            return hdfsPath;
        } finally {
            IOUtils.closeStream(in);
        }
    }

}
