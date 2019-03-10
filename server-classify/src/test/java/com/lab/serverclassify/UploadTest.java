package com.lab.serverclassify;


import com.lab.serverclassify.classify.service.impl.HDFSFileSystem;
import com.lab.serverclassify.mapper.UserUploadLogMapper;
import com.lab.serverclassify.mapper.VersionFileMapper;
import com.lab.serverclassify.pojo.domain.UserUploadLog;
import com.lab.serverclassify.pojo.domain.VersionFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerClassifyApplication.class)
@Slf4j
public class UploadTest {

    @Autowired
    private UserUploadLogMapper userUploadLogMapper;

    @Autowired
    private HDFSFileSystem fileSystem;

    @Autowired
    private VersionFileMapper versionFileMapper;

    @Test
    public void uploadFile() throws Exception {
        fileSystem.copyFile("1.txt", "/4.txt");
    }

    @Test
    public void mapperTest() {
        VersionFile versionFile = new VersionFile("bob", "3", "/home/hadoop/1.txt", 2);
        Integer id = versionFileMapper.insert(versionFile);
        System.out.println(versionFile.getId());
    }

    @Test
    public void updateTest() {
//        System.out.println(versionFileMapper.update("bob", "3", "new path"));
        System.out.println(versionFileMapper.getFilePathByUsernameAndVersion("tom", "2")==null);
    }

    @Test
    public void deleteTest() {
        System.out.println(versionFileMapper.delete("bob", "3"));
    }

    @Test
    public void getAll() {
        versionFileMapper.getAllVersionFilesByUserName("bob").forEach(System.out::println);
    }

    @Test
    public void userUploadLogMapperTest() {
        List<UserUploadLog> logs = new ArrayList<>();
        UserUploadLog log = new UserUploadLog("tom","1","/hdfs",System.currentTimeMillis());
        UserUploadLog log2 = new UserUploadLog("tom","2","/hdfs",System.currentTimeMillis());
        UserUploadLog log3 = new UserUploadLog("tom","3","/hdfs",System.currentTimeMillis());
        logs.add(log);
        logs.add(log2);
        logs.add(log3);
        logs.forEach(tmp -> {
            userUploadLogMapper.insert(tmp);
            System.out.println(tmp.getId());
        });
        userUploadLogMapper.delete("tom", "2");
        userUploadLogMapper.getAllUserUploadLogByUsername("tom").forEach(System.out::println);

    }
}
