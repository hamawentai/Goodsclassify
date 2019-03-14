package com.lab.serversearch.service;

import com.lab.serversearch.domain.Goods;
import com.lab.serversearch.vo.CommoditySearchVO;
import com.lab.serversearch.vo.ResultVO;

import java.util.List;

/**
 * 商品接口
 */
public interface GoodsService {

    /**
     * 商品搜索
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    ResultVO<CommoditySearchVO> commoditySearch(String keyword, int page, int size,List<String> label);

    boolean addGood(List<Goods> goods);
}
