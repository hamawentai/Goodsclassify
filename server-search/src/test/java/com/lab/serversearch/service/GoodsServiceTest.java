package com.lab.serversearch.service;

import com.lab.serversearch.config.PageSizeConfig;
import com.lab.serversearch.dao.GoodsSearchDao;
import com.lab.serversearch.domain.Goods;
import com.lab.serversearch.vo.CommoditySearchVO;
import com.lab.serversearch.vo.ResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsServiceTest {

    @Autowired
    private GoodsSearchDao goodsSearchDao;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PageSizeConfig pageSizeConfig;

    @Test
    public void page() {
//        System.out.println(pageSizeConfig.getPage()+" "+pageSizeConfig.getSize());
        ResultVO<CommoditySearchVO> resultVO = goodsService.commoditySearch("品牌", 1, 10, null);
        List<CommoditySearchVO> result =
                resultVO.getResult();
        System.out.println(result.size());
        result.forEach(
                res -> {
                    System.out.println(res);
                }
        );
    }


    @Test
    public void search() {
        String key = "图书";
        Pageable pageRequest = PageRequest.of(0,10);
        Page<Goods> li = goodsSearchDao.findByLabelOrDescribeLike(key, key, pageRequest);
        System.out.println(li.getSize());
        li.forEach(
                res -> {
                    System.out.println(res.toString());
                }
        );
    }

    @Test
    public void addGood() {
        Goods goods = new Goods();
        goods.setId("2");
        goods.setDescribe("I am miss you");
        goods.setLabel("图书杂志");
        goodsSearchDao.save(goods);

    }
}