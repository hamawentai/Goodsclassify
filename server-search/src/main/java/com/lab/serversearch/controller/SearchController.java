package com.lab.serversearch.controller;

import com.lab.serversearch.ao.CommoditySearchAO;
import com.lab.serversearch.config.PageSizeConfig;
import com.lab.serversearch.myenum.SearchEnum;
import com.lab.serversearch.service.GoodsService;
import com.lab.serversearch.service.LabelService;
import com.lab.serversearch.vo.CommoditySearchVO;
import com.lab.serversearch.vo.LabelVO;
import com.lab.serversearch.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索模块 控制器
 */
@Api(value = "SearchController ")
@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PageSizeConfig pageSizeConfig;

    @Autowired
    private LabelService labelService;

    /**
     * 按商品名称搜索
     * @param commoditySearchAO
     * @return
     */
    @ApiOperation(value = "商品搜索",notes = "根据商品名称进行搜索")
    @ApiImplicitParam(name = "commoditySearchAO",value = "包含关键字,类型以及分类",dataType = "CommoditySearchAO",required = true)
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

    /**
     * 获取所有一级分类
     * @return
     */
    @ApiOperation(value = "获取全部的一级分类")
    @GetMapping("/labels")
    public List<LabelVO> getAllLabel() {
        return labelService.findAllLabels();
    }
}
