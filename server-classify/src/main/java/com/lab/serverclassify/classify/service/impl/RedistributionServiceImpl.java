package com.lab.serverclassify.classify.service.impl;

import com.lab.serverclassify.classify.service.RedistributionService;
import com.lab.serverclassify.mongodb.repository.RedistributionRepository;
import com.lab.serverclassify.pojo.domain.LabelValueDo;
import com.lab.serverclassify.pojo.domain.ProvinceValueDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weixun
 * @data 19-3-14 下午6:46
 */
@Service
public class RedistributionServiceImpl implements RedistributionService {

    @Autowired
    private RedistributionRepository redistributionRepository;

    @Override
    public List<LabelValueDo> getProvinceLabelRank(Integer no, String province) {
        List<LabelValueDo> labelValueDos = redistributionRepository.findLabelRankByProvince(province);
        labelValueDos.sort((a, b) -> (int) ((Long.valueOf(a.getValue()) - Long.valueOf(b.getValue()))));
        return getList(labelValueDos, no);
    }

    @Override
    public List<ProvinceValueDO> getProvinceSalesRank(Integer no) {
        List<ProvinceValueDO> provinceValueDOS = redistributionRepository.findAllProvinceSalesRank();
        provinceValueDOS.sort((a, b) -> (int) (Long.valueOf(a.getValue()) - Long.valueOf(b.getValue())));
        return getList(provinceValueDOS, no);
    }

    @Override
    public List<LabelValueDo> getLabelSalesRank(Integer no) {
        List<LabelValueDo> labelValueDos = redistributionRepository.findAllLabelSalesRank();
        labelValueDos.sort((a, b) -> (int) (Long.valueOf(a.getValue()) - Long.valueOf(b.getValue())));
        return getList(labelValueDos, no);
    }

    private <T> List<T> getList(List<T> list, Integer no) {
        return no <= list.size() && no > 0 ? list.subList(0, no) : list;
    }
}
