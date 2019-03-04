package com.lab.serverclassify.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weixun
 */
@Data
@Component
@ConfigurationProperties(value = "wx.security.shell")
public class ShellProperties {
    private String shellPath;
    private ShellCallBackProperties[] url;
}
