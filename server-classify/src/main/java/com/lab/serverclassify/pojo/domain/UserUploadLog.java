package com.lab.serverclassify.pojo.domain;

import lombok.Data;

/**
 * @author weixun
 */
@Data
public class UserUploadLog {

    private Integer id;
    private String userName;
    private String version;
    private String uploadFilePath;
    private Long time;


    public UserUploadLog() {
    }

    public UserUploadLog(String userName, String version, String uploadFilePath, Long time) {
        this.userName = userName;
        this.version = version;
        this.uploadFilePath = uploadFilePath;
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserUploadLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", version='" + version + '\'' +
                ", uploadFilePath='" + uploadFilePath + '\'' +
                ", time=" + time +
                '}';
    }
}
