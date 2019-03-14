package com.lab.serversearch.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * kafka生产者配置装载
 */
@Data
@Component
@ConfigurationProperties(value = "mykafka.producter")
public class ProducerProperties {

    private String nodes;
    private Integer retries;
    private Integer batchSize;
    private Integer lingerMs;
    private Integer bufferMemory;

    @Override
    public String toString() {
        return "ProducerProperties{" +
                "nodes='" + nodes + '\'' +
                ", retries=" + retries +
                ", batchSize=" + batchSize +
                ", lingerMs=" + lingerMs +
                ", bufferMemory=" + bufferMemory +
                '}';
    }
}
