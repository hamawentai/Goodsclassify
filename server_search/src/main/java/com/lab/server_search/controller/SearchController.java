package com.lab.server_search.controller;

import com.lab.server_search.ao.CommoditySearchAO;
import com.lab.server_search.config.PageSizeConfig;
import com.lab.server_search.myEnum.SearchEnum;
import com.lab.server_search.service.GoodsService;
import com.lab.server_search.vo.CommoditySearchVO;
import com.lab.server_search.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索模块 控制器
 */
@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PageSizeConfig pageSizeConfig;

    /**
     * 按商品名称搜索
     * @param commoditySearchAO
     * @return
     */
    @PostMapping("/commodity")
    public ResultVO<CommoditySearchVO> commoditySearch(@RequestBody CommoditySearchAO commoditySearchAO) {
        String keyWord = commoditySearchAO.getKey();
        List<String> label = commoditySearchAO.getLable();
        String type = commoditySearchAO.getType();

        if (type.equals(SearchEnum.COMMODITY_SEARCH.getType())) {
            return goodsService.commoditySearch(keyWord,pageSizeConfig.getPage(),
                    pageSizeConfig.getSize(),label);
        }
        return null;
    }
}
