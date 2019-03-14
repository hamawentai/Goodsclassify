package com.lab.serversearch.service.impl;

import com.lab.serversearch.domain.Catalog;
import com.lab.serversearch.domain.Label;
import com.lab.serversearch.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addLabel(List<Label> labelList) {
        redisTemplate.opsForValue().set("labelList",labelList);
    }

    @Override
    public void addCatalog(Catalog catalog) {
        redisTemplate.opsForValue().set(catalog.getLabelName(),catalog);
    }

    @Override
    public List<Label> getLabel() {
        List<Label> labelList = (List<Label>) redisTemplate.opsForValue().get("labelList");
        return labelList;
    }

    @Override
    public Catalog getCatalog(String catalog) {
        return (Catalog) redisTemplate.opsForValue().get(catalog);
    }
}
