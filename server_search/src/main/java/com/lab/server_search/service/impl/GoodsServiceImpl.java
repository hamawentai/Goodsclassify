package com.lab.server_search.service.impl;

import com.lab.server_search.dao.GoodsSearchDao;
import com.lab.server_search.document.Goods;
import com.lab.server_search.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsSearchDao goodsSearchDao;

    @Override
    public Page<Goods> search(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return goodsSearchDao.findByLabelAndDescribeLike(keyword,keyword,pageRequest);
    }

    @Override
    public boolean addGood(List<Goods> goods) {
        for (Goods good : goods) {
            goodsSearchDao.save(good);
        }
        return true;
    }
}
