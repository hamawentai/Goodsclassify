package com.lab.serversearch.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsLabel implements Serializable {
    private String rowKey;
    private String label;
    private String describe;
}
