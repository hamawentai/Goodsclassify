package com.lab.server_search.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HbaseServiceTest {

    @Autowired
    private HbaseService hbaseService;

    @Test
    public void find() {
        List<Map<String, Object>> fileTable = hbaseService.find("goodslabel", "1", "10");
        fileTable.forEach(
                map->{
                    map.forEach(
                            (k,v)->{
                                System.out.println(k+" "+v);
                            }
                    );
                }
        );
    }
}