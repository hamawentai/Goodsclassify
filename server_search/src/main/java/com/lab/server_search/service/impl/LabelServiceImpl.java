package com.lab.server_search.service.impl;

import com.lab.server_search.dao.LabelDao;
import com.lab.server_search.domain.Label;
import com.lab.server_search.service.LabelService;
import com.lab.server_search.service.RedisService;
import com.lab.server_search.vo.LabelVO;
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
