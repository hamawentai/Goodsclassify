package com.lab.serversearch.service.impl;

import com.lab.serversearch.dao.LabelDao;
import com.lab.serversearch.domain.Label;
import com.lab.serversearch.service.LabelService;
import com.lab.serversearch.service.RedisService;
import com.lab.serversearch.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private RedisService redisService;

    @Override
    public List<LabelVO> findAllLabels() {

        List<Label> labelList = redisService.getLabel();

        if (labelList == null) {
            labelList = labelDao.findAll();
            redisService.addLabel(labelList);
        }

        List<LabelVO> labelVOList = new ArrayList<>();
        for (Label label : labelList) {
            LabelVO labelVO = new LabelVO();
            labelVO.setType(label.getLabel());
            labelVOList.add(labelVO);
        }
        return labelVOList;
    }
}
