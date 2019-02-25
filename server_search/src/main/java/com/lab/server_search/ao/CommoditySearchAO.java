package com.lab.server_search.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品搜索: 前端传来的json格式数据
 *  key: 关键字
 *  type: 搜索类型
 *      1: 商品
 *      2: 标签
 *  lable: 一级分类
 */
@Data
public class CommoditySearchAO implements Serializable {

    private String key;
    private String type;
    private List<String> lable;
}
