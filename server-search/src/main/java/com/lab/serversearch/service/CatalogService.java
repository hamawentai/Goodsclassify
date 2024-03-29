package com.lab.serversearch.service;

/**
 * 标签服务
 */
public interface CatalogService {

    /**
     * 获取标签对应的ID
     * @param name
     * @return
     */
    Integer getCatalogId(String name);
}
