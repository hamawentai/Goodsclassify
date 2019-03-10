package com.lab.serverclassify.classify.service.impl;


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

    private Configuration conf;

//    public HDFSFileSystem() {
//        System.out.println("_------------+__________"+hdfsProperties.getDefaultFS());
//        conf = new Configuration();
//        conf.set("fs.defaultFS", hdfsProperties.getDefaultFS());
//    }


    /**
     * 按路径上传文件到hdfs
     */
    public void copyFile(String local, String remote) throws IOException {
        conf = new Configuration();
        conf.set("fs.defaultFS", hdfsProperties.getDefaultFS());
        FileSystem fs = FileSystem.get(conf);
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        log.info("copy from: " + local + " to " + remote);
        fs.close();
    }

    /**
     * 按路径下载hdfs上的文件
     */
    public void download(String remote, String local) throws IOException {
        conf = new Configuration();
        conf.set("fs.defaultFS", hdfsProperties.getDefaultFS());
        FileSystem fs = FileSystem.get(conf);
        fs.copyToLocalFile(new Path(remote), new Path(local));
        log.info("download: from" + remote + " to " + local);
        fs.close();
    }

    /**
     * File对象上传到hdfs
     */
    public String createFile(InputStream in, String username, String filename, String kind) throws IOException {
        conf = new Configuration();
        conf.set("fs.defaultFS", hdfsProperties.getDefaultFS());
        String[] date = TimeUtils.getNowTime().split("\\|");
        String hdfsPath = "/" + kind + "/" + username + "/" + date[0] + "/" + filename + "_" + date[1];
        try {
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
