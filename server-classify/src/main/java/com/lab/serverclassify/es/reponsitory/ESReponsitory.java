package com.lab.serverclassify.es.reponsitory;


import com.lab.serverclassify.pojo.document.GoodsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @author weixun
 */
@Repository
public class ESReponsitory {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    public List<GoodsUser> getBy(String username, String operation) {
        Pageable pageable = PageRequest.of(0, 3);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("user", username)).withQuery(termQuery("version", operation)).withPageable(pageable).build();
        List<GoodsUser> list = esTemplate.queryForList(searchQuery, GoodsUser.class);
        return list;
    }

}
