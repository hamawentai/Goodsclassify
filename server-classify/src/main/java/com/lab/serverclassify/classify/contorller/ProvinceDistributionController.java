package com.lab.serverclassify.classify.contorller;

import com.lab.serverclassify.mongodb.repository.RedistributionRepository;
import com.lab.serverclassify.pojo.domain.LabelValueDo;
import com.lab.serverclassify.pojo.domain.ProvinceValueDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private RedistributionRepository redistributionRepository;

    @GetMapping("/label_sales_rank")
    public List<LabelValueDo> getSalesRankByLabel3(String label) {
        return redistributionRepository.findByLabel(label);
    }

    @GetMapping("/sales_rank")
    public List<ProvinceValueDO> getProvinceSalesRank() {
        return redistributionRepository.findAllProvinceSalesRank();
    }
}
