package com.lab.serverclassify.pojo.bo;


import lombok.Data;

import java.util.List;

/**
 * @author weixun
 */
@Data
public class ResultFileBo {

    private List<ResultFileRowBO> rows;
    private Long len;
}
