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
        conf.set("fs.defaultFS", "hdfs://10.0.0.36:9000");
        System.setProperty("HADOOP_USER_NAME", "root");
        fs = FileSystem.get(conf);
    }

    public void uploadFile() throws Exception {
        fs.copyFromLocalFile(new Path("E:/C/dd.jpg"), new Path("/21.jpg"));
        fs.close();
    }

    public static void main(String[] args) throws Exception{
        HdaoopClientDemo hadoop = new HdaoopClientDemo();
        hadoop.init();
        hadoop.uploadFile();
    }

    public void downloadFile() throws Exception{
       fs.copyToLocalFile(new Path("/ee.jpg"), new Path("da.jpg"));
        FSDataInputStream fsdi = fs.open(new Path("/1.txt"));
        IOUtils.copy(fsdi, System.out);
    }
}
