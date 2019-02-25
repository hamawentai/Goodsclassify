package com.lab.server_search.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品搜索: 后端返回的数据
 *  name: 商品名
 *  label: 标签
 * 返回给前端数据为: ResultVO<CommoditySearchVO>
 */
@Data
public class CommoditySearchVO implements Serializable {
    private String name;
    private String label;
}