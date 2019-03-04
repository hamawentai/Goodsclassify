package com.lab.serverclassify.kafka.serviceImpl;

import com.google.gson.Gson;
import com.lab.serverclassify.pojo.dto.ShellCommandsDTO;
import com.lab.serverclassify.kafka.MyKafkaProducer;
import com.lab.serverclassify.kafka.service.KafkaSenderService;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weixun
 */
@Component(value = "kafkaShellCommandsSender")
public class KafkaShellCommandsSenderImpl implements KafkaSenderService<ShellCommandsDTO> {

    @Autowired
    private MyKafkaProducer<String, String> strStrKafkaProducer;

    @Override
    public void sendMsg(String topic, ShellCommandsDTO msg) {
        Gson gson = strStrKafkaProducer.getGson();
        Producer<String, String> producer = strStrKafkaProducer.getProducer();
        String msgJson = gson.toJson(msg);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, msgJson);
        producer.send(producerRecord);
    }
}
