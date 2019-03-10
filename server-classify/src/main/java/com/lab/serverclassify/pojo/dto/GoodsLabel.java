package com.lab.serverclassify.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsLabel implements Serializable {
    private String rowKey;
    private String label;
    private String describe;
}
