package com.lab.server_search.service;

import com.lab.server_search.document.Goods;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品接口
 */
public interface GoodsService {

    Page<Goods> search(String keyword,int page,int size);

    boolean addGood(List<Goods> goods);
}
