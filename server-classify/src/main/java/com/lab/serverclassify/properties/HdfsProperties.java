package com.lab.serverclassify.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weixun
 */
@Data
@Component
@ConfigurationProperties(value = "wx.security.hdfs")
public class HdfsProperties {
    private String hdfsRoot;
    private String defaultFS;
    private String uploadPath;
}
