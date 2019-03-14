package com.lab.serverclassify;


import com.lab.serverclassify.mongodb.repository.RedistributionRepository;
import com.lab.serverclassify.mongodb.repository.UserFilesAdvancedRepository;
import com.lab.serverclassify.mongodb.repository.UserFilesRepository;
import com.lab.serverclassify.pojo.domain.UserFilesDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerClassifyApplication.class)
public class MongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserFilesAdvancedRepository advancedRepository;

    @Autowired
    private UserFilesRepository repository;

    @Autowired
    private RedistributionRepository redistributionRepository;

    @Test
    public void mongodb() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("jack"));
        UserFilesDO userFilesDO = mongoTemplate.findOne(query, UserFilesDO.class,"file");
        System.out.println(userFilesDO);
    }

    @Test
    public void labelSalesRankTest() {
        System.out.println(redistributionRepository.findAllProvinceSalesRank());
        System.out.println(redistributionRepository.findAllLabelSalesRank());
    }

}
