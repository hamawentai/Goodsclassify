package com.lab.serverclassify.pojo.bo;


import lombok.Data;


/**
 * @author weixun
 */
@Data
public class ResultFileRowBO {

    private byte[] label;
    private byte[] describe;
    private Integer len;
}
