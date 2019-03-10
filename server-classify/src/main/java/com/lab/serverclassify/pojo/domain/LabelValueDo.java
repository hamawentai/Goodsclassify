package com.lab.serverclassify.pojo.domain;

import lombok.Data;

/**
 * @author weixun
 * @data 19-3-10 下午7:33
 */
@Data
public class LabelValueDo {

    private String label;
    private String value;


    public LabelValueDo() {
    }

    public LabelValueDo(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return "LabelValueDo{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
