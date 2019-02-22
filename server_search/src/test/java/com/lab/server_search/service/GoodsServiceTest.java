package com.lab.server_search.service;

import com.lab.server_search.dao.GoodsSearchDao;
import com.lab.server_search.document.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsServiceTest {

    @Autowired
    private GoodsSearchDao goodsSearchDao;

    @Test
    public void search() {
        Iterable<Goods> all = goodsSearchDao.findAll();
    }

    @Test
    public void addGood() {
    }
}