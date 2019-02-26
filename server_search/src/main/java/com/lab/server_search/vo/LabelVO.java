package com.lab.server_search.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LabelVO implements Serializable {
    /**
     * 一级分类名称
     */
    private String type;
}
