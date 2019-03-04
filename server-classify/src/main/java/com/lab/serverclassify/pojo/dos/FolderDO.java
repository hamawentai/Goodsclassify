package com.lab.serverclassify.pojo.dos;

import lombok.Data;

import java.util.List;

/**
 * @author weixun
 */
@Data
public class FolderDO {
    private String fileName;
    private Integer type;
    private List<FolderDO> list;

    public FolderDO() {
    }

    public FolderDO(String fileName, Integer type, List<FolderDO> list) {
        this.fileName = fileName;
        this.type = type;
        this.list = list;
    }

    @Override
    public String toString() {
        return "FolderDO{" +
                "fileName='" + fileName + '\'' +
                ", type=" + type +
                ", list=" + list +
                '}';
    }
}
