package com.lab.serverclassify.pojo.domain;

import lombok.Data;

/**
 * @author weixun
 */
@Data
public class VersionFile {

    private Integer id;
    private String userName;
    private String version;
    private String filePath;
    private Integer kind;

    public VersionFile() {
    }

    public VersionFile(String userName, String version, String filePath, Integer kind) {
        this.userName = userName;
        this.version = version;
        this.filePath = filePath;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "VersionFile{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", version='" + version + '\'' +
                ", filePath='" + filePath + '\'' +
                ", kind=" + kind +
                '}';
    }
}
