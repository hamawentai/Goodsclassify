package com.lab.serverclassify.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

public class HdfsFileSystem {


    /**
     * 按路径上传文件到hdfs
     */
    public static void copyFile(Configuration conf, String uri, String local, String remote) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        System.out.println("copy from: " + local + " to " + remote);
        fs.close();
    }

    /**
     * 按路径下载hdfs上的文件
     */
    public static void download(Configuration conf, String uri, String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        fs.copyToLocalFile(path, new Path(local));
        System.out.println("download: from" + remote + " to " + local);
        fs.close();
    }

    /**
     * File对象上传到hdfs
     */
    public static void createFile(InputStream in, String username, String filename) throws IOException {
        String[] date = TimeUtils.getNowTime().split("\\|");
        String hdfsPath = "/upload/"+username + "/" + date[0] + "/" + filename + "-" + date[1];
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://hadoop1:9000");
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf);
            FSDataOutputStream out = fileSystem.create(new Path(hdfsPath));
            IOUtils.copyBytes(in, out, 4096, false);
            out.hsync();
            out.close();
            System.out.println("create file in hdfs:" + hdfsPath);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}