package com.lab.server_search.service;

import com.lab.server_search.vo.LabelVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

import java.util.List;

import static org.junit.Assert.*;

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