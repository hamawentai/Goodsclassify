package com.lab.server_search.service;

import com.lab.server_search.domain.Goods;
import com.lab.server_search.vo.CommoditySearchVO;
import com.lab.server_search.vo.ResultVO;
import org.springframework.data.domain.Page;

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
