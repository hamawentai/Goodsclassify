package com.lab.server_search.dao;

import com.lab.server_search.document.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsSearchDao extends ElasticsearchRepository<Goods,String> {

    /**
     * 按照类别 和 商品查询
     * @param label
     * @param describe
     * @param pageable
     * @return
     */
    Page<Goods> findByLabelAndDescribeLike(String label, String describe, Pageable pageable);
}
