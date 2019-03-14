package com.lab.serversearch.service;

import com.lab.serversearch.domain.Catalog;
import com.lab.serversearch.domain.Label;

import java.util.List;

public interface RedisService {

    /**
     * 将一级分类添加到缓存
     * @param labelList
     */
    void addLabel(List<Label> labelList);

    /**
     * 将标签添加到缓存
     * @param catalog
     */
    void addCatalog(Catalog catalog);

    /**
     * 获取所有一级分类
     * @return
     */
    List<Label> getLabel();

    /**
     * 获取标签
     * @param catalog
     * @return
     */
    Catalog getCatalog(String catalog);
}
