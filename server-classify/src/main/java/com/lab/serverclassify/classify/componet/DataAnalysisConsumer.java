package com.lab.serverclassify.classify.componet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lab.serverclassify.classify.service.RunShellService;
import com.lab.serverclassify.pojo.dto.ShellCommandsDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author weixun
 */
@Component
@Slf4j
public class DataAnalysisConsumer {

    @Autowired
    private RunShellService runShellService;

    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = {"shell"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            ShellCommandsDTO commandsDTO = gson.fromJson((String) kafkaMessage.get(), ShellCommandsDTO.class);
            runShellService.runDataAnalysisShell(commandsDTO);
            log.info("commandsDTO =" + commandsDTO);

        }
    }
}
