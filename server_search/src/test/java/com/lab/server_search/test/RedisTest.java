package com.lab.server_search.test;

import com.lab.server_search.domain.Label;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("wx",12);

        Integer age = (Integer) redisTemplate.opsForValue().get("wx");
        System.out.println(age);
    }

    @Test
    public void addList() {

        Label label = new Label();
        label.setId(1);
        label.setLabel("母婴用品");
        List<Label> labels = new ArrayList<>();
        labels.add(label);
        redisTemplate.opsForValue().set("label",labels);
        List<Label> labelList = (List<Label>) redisTemplate.opsForValue().get("label");
        System.out.println("size "+labelList.size());
        for (Label label1:labelList) {
            System.out.println(label);
        }
        redisTemplate.delete("label");
    }
}
