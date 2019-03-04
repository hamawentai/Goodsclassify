package com.lab.serverclassify.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weixun
 */
@Data
@Component
@ConfigurationProperties(value = "wx.security.kafka")
public class MyKafkaProperties {
    private String bootstrapServers;

}
