//package com.lab.server_search.service.impl;
//
//import com.lab.server_search.document.Doc;
//import com.lab.server_search.service.HbaseService;
//import com.lab.server_search.util.HBaseDoc;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class HbaseServiceImplTest {
//
//    @Autowired
//    private HbaseService hbaseService;
//
//    @Test
//    public void find() {
//        List<Map<String, Object>> maps = hbaseService.find(HBaseDoc.TABLE_NAME, "", "");
//        maps.forEach(
//                map->{
//                    map.forEach(
//                            (k,v)->{
//                                System.out.println(k+" "+v);
//                            }
//                    );
//                }
//        );
//    }
//
//    @Test
//    public void insert() {
//        List<Doc> docs = null;
//        try {
//            docs = hbaseService.getDocs();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        hbaseService.insert(docs, HBaseDoc.TABLE_NAME);
//    }
//
//    @Test
//    public void getDocs() {
//    }
//}