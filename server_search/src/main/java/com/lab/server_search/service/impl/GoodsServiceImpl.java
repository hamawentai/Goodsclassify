package com.lab.server_search.service.impl;

import com.lab.server_search.dao.GoodsSearchDao;
import com.lab.server_search.domain.Goods;
import com.lab.server_search.service.GoodsService;
import com.lab.server_search.vo.CommoditySearchVO;
import com.lab.server_search.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsSearchDao goodsSearchDao;

    @Override
    public ResultVO<CommoditySearchVO> commoditySearch(String keyword, int page, int size, List<String> label) {
        ResultVO<CommoditySearchVO> resultVO = new ResultVO<>();
        List<CommoditySearchVO> searchVOList = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(page-1,size);

        //按商品名搜索
        Page<Goods> searchGoods = goodsSearchDao.findByLabelOrDescribeLike(keyword, keyword, pageRequest);

        //装填数据
        for (Goods goods : searchGoods) {
            if (label!=null) {
                String currLabel = goods.getLabel().split("--")[0];
                if (label.contains(currLabel)) {
                    CommoditySearchVO commoditySearchVO = new CommoditySearchVO();
                    commoditySearchVO.setLabel(goods.getLabel());
                    commoditySearchVO.setName(goods.getDescribe());
                    searchVOList.add(commoditySearchVO);
                }
            }
        }

        resultVO.setResult(searchVOList);

        return resultVO;
    }

    @Override
    public boolean addGood(List<Goods> goods) {
        for (Goods good : goods) {
            goodsSearchDao.save(good);
        }
        return true;
    }
}
