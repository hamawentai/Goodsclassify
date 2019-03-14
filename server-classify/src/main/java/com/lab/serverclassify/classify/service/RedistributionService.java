package com.lab.serverclassify.classify.service;

import com.lab.serverclassify.pojo.domain.LabelValueDo;
import com.lab.serverclassify.pojo.domain.ProvinceValueDO;

import java.util.List;

/**
 * @author weixun
 * @data 19-3-14 下午6:37
 */
public interface RedistributionService {

    /**
     * 根据省份名返回各个省的各种分类的销量排行
     *
     * @param no       需要前几个数据
     * @param province 省份名
     * @return 各个标签的销量排行
     */
    List<LabelValueDo> getProvinceLabelRank(Integer no, String province);

    /**
     * 获得省份的销量排行
     *
     * @param no 需要前几个数据
     * @return 省份-销量
     */
    List<ProvinceValueDO> getProvinceSalesRank(Integer no);

    /**
     * 获得全国商品类型的销量排行
     *
     * @param no 需要前几个数据
     * @return 销量排行
     */
    List<LabelValueDo> getLabelSalesRank(Integer no);
}
