package com.lab.serverclassify.pojo.domain;

import lombok.Data;

/**
 * @author weixun
 * @data 19-3-10 下午9:01
 */
@Data
public class ProvinceValueDO {
    private String province;
    private String value;


    public ProvinceValueDO() {
    }

    public ProvinceValueDO(String province, String value) {
        this.province = province;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProvinceValueDO{" +
                "province='" + province + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
