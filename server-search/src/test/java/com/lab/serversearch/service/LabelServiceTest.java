package com.lab.serversearch.service;

import com.lab.serversearch.vo.LabelVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelServiceTest {

    @Autowired
    private LabelService labelService;

    @Test
    public void findAll(){
        List<LabelVO> allLabels = labelService.findAllLabels();
        allLabels.forEach(
                res -> {
                    System.out.println(res.getType());
                }
        );
    }
}