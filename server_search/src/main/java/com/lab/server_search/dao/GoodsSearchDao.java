package com.lab.server_search.dao;

import com.lab.server_search.domain.Goods;
import org.springframework.data.domain.Page;
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
    Page<Goods> findByLabelOrDescribeLike(String label, String describe, Pageable pageable);

    void delete(Goods goods);
}
