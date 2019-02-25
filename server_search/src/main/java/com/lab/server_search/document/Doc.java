package com.lab.server_search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 测试es的实体类
 */
@Data
@Document(indexName = "tfjt",type = "doc")
public class Doc {

	@Id
	private Integer id;

	@Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String title;

	@Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String describe;

	@Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String author;
}