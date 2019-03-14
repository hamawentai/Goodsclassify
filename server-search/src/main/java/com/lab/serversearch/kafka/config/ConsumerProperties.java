package com.lab.serversearch.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "mykafka.consumer")
public class ConsumerProperties {

    private String bootstrapServers;
    private Boolean enableAutoCommit;
    private String autoCommitIntervalMs;
    private String sessionTimeoutMs;
    private String groupId;
    private String autoOffsetReset;

    @Override
    public String toString() {
        return "ConsumerProperties{" +
                "bootstrapServers='" + bootstrapServers + '\'' +
                ", enableAutoCommit=" + enableAutoCommit +
                ", autoCommitIntervalMs='" + autoCommitIntervalMs + '\'' +
                ", sessionTimeoutMs='" + sessionTimeoutMs + '\'' +
                ", groupId='" + groupId + '\'' +
                ", autoOffsetReset='" + autoOffsetReset + '\'' +
                '}';
    }
}
