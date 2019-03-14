package com.lab.serversearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 分类查询 文档
 */
@Data
@Document(indexName = "classify",type = "goods")
public class Goods implements Serializable {
    @Id
    private String id;

    /**
     * 标签
     */
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String label;

    /**
     * 商品名
     */
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String describe;
}
