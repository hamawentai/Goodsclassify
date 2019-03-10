package com.lab.serverclassify.classify.service;

/**
 * @author weixun
 * @date 19-3-7 下午3:37
 */
public interface UploadFileLogService {

    /**
     * 记录用户上传文件的日志
     * @param username 用户名
     * @param uploadFilePath 文件存储路径
     * @return 受影响的列
     */
    Integer recordUploadFileLog(String username, String uploadFilePath);
}
