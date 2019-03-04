package com.lab.serverclassify.pojo.dos;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author weixun
 */
@Data
public class UserFilesDO {
    @Id
    private String id;
    private String name;
    private FolderDO files;

    public UserFilesDO() {
    }

    public UserFilesDO(String id, String name, FolderDO files) {
        this.id = id;
        this.name = name;
        this.files = files;
    }

    @Override
    public String toString() {
        return "UserFilesDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", files=" + files +
                '}';
    }
}
