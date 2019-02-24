package com.lab.server_search.dao;

import com.lab.server_search.document.Doc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 访问层
 */
public interface DocSearchDao extends ElasticsearchRepository<Doc,Integer> {

    Page<Doc> findAll(Pageable pageable);

    Page<Doc> findByTitleOrAuthorOrDescribe(String title,String author,String describe,Pageable pageable);
}
