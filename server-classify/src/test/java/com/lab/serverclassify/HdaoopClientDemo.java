package com.lab.serverclassify;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;

public class HdaoopClientDemo {

    private FileSystem fs = null;

    @Before
    public void init() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://wx:9000");
        System.setProperty("hadoop.home.dir", "/home/hadoop/apps/hadoop/");
        fs = FileSystem.get(conf);
    }

    public void uploadFile() throws Exception {
        fs.copyFromLocalFile(new Path("server-classify/1.txt"), new Path("/3.txt"));
        fs.close();
    }


    public static void main(String[] args) throws Exception {
        HdaoopClientDemo hadoop = new HdaoopClientDemo();
        hadoop.init();
        hadoop.uploadFile();
    }

    public void downloadFile() throws Exception {
        fs.copyToLocalFile(new Path("/ee.jpg"), new Path("da.jpg"));
        FSDataInputStream fsdi = fs.open(new Path("/1.txt"));
        IOUtils.copy(fsdi, System.out);
    }
}
