package com.lab.serverclassify.classify.contorller;

import com.lab.serverclassify.classify.service.RedistributionService;
import com.lab.serverclassify.pojo.domain.LabelValueDo;
import com.lab.serverclassify.pojo.domain.ProvinceValueDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weixun
 * @data 19-3-10 下午7:24
 */

@RestController
@RequestMapping("/province")
@Slf4j
public class ProvinceDistributionController {


    @Autowired
    private RedistributionService redistributionService;

    @GetMapping("/province_label_rank")
    public List<LabelValueDo> getSalesRankByProvince(@RequestParam(defaultValue = "5") Integer no, String province) {
        System.out.println(no + " " + province);
        return redistributionService.getProvinceLabelRank(no, province);
    }

    @GetMapping("/province_sales_rank")
    public List<ProvinceValueDO> getProvinceSalesRank(@RequestParam(defaultValue = "5") Integer no) {
        return redistributionService.getProvinceSalesRank(no);
    }

    @GetMapping("/label_sales_rank")
    public List<LabelValueDo> getLabelSalesRank(@RequestParam(defaultValue = "5") Integer no) {
        return redistributionService.getLabelSalesRank(no);
    }


}
