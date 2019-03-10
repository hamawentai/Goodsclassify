package com.lab.serverclassify.classify.service.impl;

import com.lab.serverclassify.classify.service.UploadFileLogService;
import com.lab.serverclassify.mapper.UserUploadLogMapper;
import com.lab.serverclassify.pojo.domain.UserUploadLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weixun
 * @date 19-3-7 下午3:42
 */
@Service
public class UploadFileLogServiceImpl implements UploadFileLogService {

    @Autowired
    private UserUploadLogMapper userUploadLogMapper;

    @Override
    public Integer recordUploadFileLog(String username, String uploadFilePath) {
        long time = System.currentTimeMillis();
        String version = "1";
        UserUploadLog log = new UserUploadLog(username, version, uploadFilePath, time);
        return userUploadLogMapper.insert(log);
    }
}
