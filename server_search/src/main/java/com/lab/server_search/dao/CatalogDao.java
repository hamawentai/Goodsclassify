package com.lab.server_search.dao;

import com.lab.server_search.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogDao extends JpaRepository<Catalog,Integer> {
    /**
     * 根据标签名查询Id
     * @param labelName
     * @return
     */
    Catalog findByLabelName(String labelName);
}
