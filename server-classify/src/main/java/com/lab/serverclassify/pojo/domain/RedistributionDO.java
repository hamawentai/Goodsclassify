package com.lab.serverclassify.pojo.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author weixun
 * @data 19-3-10 下午8:06
 */
@Data
public class RedistributionDO {
    @Id
    private ObjectId id;
    private String province;
    private List<LabelValueDo> detail;
}
