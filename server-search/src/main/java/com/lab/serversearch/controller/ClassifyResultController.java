package com.lab.serversearch.controller;

import com.lab.serversearch.service.GoodsUserService;
import com.lab.serversearch.vo.CatalogResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户分类结果查询
 */
@RequestMapping("/classify")
@RestController
public class ClassifyResultController {

    @Autowired
    private GoodsUserService goodsUserService;

    @GetMapping("/result")
    public List<CatalogResultVO> getUserResult(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                               @RequestParam(value = "size",required = false,defaultValue = "15") Integer size) {
        List<CatalogResultVO> result = goodsUserService.getGoodsLabel("bob", page, size);
        return result;
    }
}
