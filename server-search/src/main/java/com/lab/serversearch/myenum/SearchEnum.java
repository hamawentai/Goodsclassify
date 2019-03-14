package com.lab.serversearch.myenum;

import lombok.Getter;

/**
 * 搜索模块枚举类
 */
@Getter
public enum SearchEnum {

    COMMODITY_SEARCH("商品","1");


    private String type;
    private String des;

    private SearchEnum(String des,String type) {
        this.des = des;
        this.type = type;
    }


}
