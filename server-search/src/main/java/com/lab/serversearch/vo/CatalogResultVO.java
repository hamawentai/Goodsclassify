package com.lab.serversearch.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户分类结果返回类
 */
@Data
public class CatalogResultVO implements Serializable {
    private String name;
    private Long pos;
    private String labelA;
    private String labelB;
    private String labelC;
}
