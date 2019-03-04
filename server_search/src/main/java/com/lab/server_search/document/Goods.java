package com.lab.server_search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 分类查询 文档
 */
@Data
@Document(indexName = "classify",type = "goods")
public class Goods {
    @Id
    private String id;

    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String label;

    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String describe;
}
