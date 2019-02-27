package com.lab.server_search.service.impl;

import com.lab.server_search.dao.CatalogDao;
import com.lab.server_search.domain.Catalog;
import com.lab.server_search.service.CatalogService;
import com.lab.server_search.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Autowired
    private RedisService redisService;

    @Override
    public Integer getCatalogId(String name) {
        Catalog catalog = redisService.getCatalog(name);
        if (catalog == null) {
            catalog = catalogDao.findByLabelName(name);
            redisService.addCatalog(catalog);
        }
        return catalog.getId();
    }
}
