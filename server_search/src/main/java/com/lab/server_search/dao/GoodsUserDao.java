package com.lab.server_search.dao;

import com.lab.server_search.domain.GoodsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsUserDao extends ElasticsearchRepository<GoodsUser,String> {

    /**
     * 根据用户名获取多条数据
     * @param userName
     * @param pageable
     * @return
     */
    Page<GoodsUser> findByUserAndVersion(String userName,String version,Pageable pageable);

}
