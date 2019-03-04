package com.lab.serverclassify.kafka;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author weixun
 */
@Data
public class MyKafkaProducer<K, V> {

    private Gson gson = new GsonBuilder().create();

    private Producer<K, V> producer;

    public MyKafkaProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "wx:9092,twl:9092,zdp:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
    }


    public MyKafkaProducer(Class<? extends Serializer> key, Class<? extends Serializer> value) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "wx:9092,twl:9092,zdp:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value.getName());
        producer = new KafkaProducer<>(properties);
    }

}
