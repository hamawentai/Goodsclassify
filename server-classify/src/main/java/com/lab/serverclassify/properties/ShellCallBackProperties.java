package com.lab.serverclassify.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weixun
 */
@Data
@Component
@ConfigurationProperties(value = "wx.security.shell.url")
public class ShellCallBackProperties {
    private String shellName;
    private String mainClass;
    private String successUrl;
    private String failUrl;
}
