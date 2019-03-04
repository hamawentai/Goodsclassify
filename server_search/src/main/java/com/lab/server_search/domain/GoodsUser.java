package com.lab.server_search.domain;

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
}
