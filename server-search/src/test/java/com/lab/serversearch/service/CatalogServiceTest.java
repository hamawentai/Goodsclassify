package com.lab.serversearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceTest {

    @Autowired
    private CatalogService catalogService;

    @Test
    public void getId() {
        Integer catalogId = catalogService.getCatalogId("食品/饮料/酒水--进口食品--饼干蛋糕");
        System.out.println(catalogId);
    }
}