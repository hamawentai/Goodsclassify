package com.lab.server_search.service;

public interface CatalogService {

    /**
     * 获取标签对应的ID
     * @param name
     * @return
     */
    Integer getCatalogId(String name);
}
