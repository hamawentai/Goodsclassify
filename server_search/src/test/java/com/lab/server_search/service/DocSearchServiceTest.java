package com.lab.server_search.service;

import com.lab.server_search.document.Doc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocSearchServiceTest {

    @Autowired
    private DocSearchService docSearchService;

    @Test
    public void findAll() {
        Page<Doc> page = docSearchService.findAll();
        page.getContent().forEach(
                doc -> {
                    System.out.println(doc);
                }
        );
        System.out.println(page);
    }

    @Test
    public void addDoc() {
        Doc doc = new Doc();
        doc.setId(5);
        doc.setAuthor("weixuan");
        doc.setDescribe("此乃楞头一个");
        doc.setTitle("天王盖地虎");
        docSearchService.addDoc(doc);
    }

    @Test
    public void findByKeyWord() {
        Page<Doc> result = docSearchService.findByKeyWord("乃楞", 1, 4);
        result.getContent().forEach(
                res -> {
                    System.out.println(res);
            }
        );
    }
}