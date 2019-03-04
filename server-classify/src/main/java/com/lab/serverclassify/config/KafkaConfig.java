package com.lab.serverclassify.config;

import com.lab.serverclassify.kafka.MyKafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weixun
 */
@Configuration
public class KafkaConfig {

    @Bean
    public MyKafkaProducer strStrKafkaProducer() {
        return new MyKafkaProducer<String, String>(StringSerializer.class, StringSerializer.class);
    }

}
