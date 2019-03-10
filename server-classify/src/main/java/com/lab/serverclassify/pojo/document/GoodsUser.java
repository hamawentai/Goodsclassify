package com.lab.serverclassify.pojo.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;
/**
 * 存储了(RowKey,UserName)
 * 方便根据用户名获取RowKey
 */
@Data
@Document(indexName = "goods",type = "goods-user")
public class GoodsUser {

    @Id
    private String id;

    private String user;

    private String version;

    @Override
    public String toString() {
        return "GoodsUser{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
