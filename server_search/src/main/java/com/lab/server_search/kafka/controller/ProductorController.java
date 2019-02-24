package com.lab.server_search.kafka.controller;

import com.lab.server_search.kafka.config.ConsumerProperties;
import com.lab.server_search.kafka.config.ProducerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductorController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ProducerProperties producerProperties;

    @Autowired
    private ConsumerProperties consumerProperties;

    @GetMapping("send")
    public String send() {
        kafkaTemplate.send("test-topic","hello");
        return "success";
    }

    @GetMapping("producer")
    public String producer() {
        String res = producerProperties.toString();
        return res;
    }

    @GetMapping("consumer")
    public String consumer() {
        return consumerProperties.toString();
    }
}
