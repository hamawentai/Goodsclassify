package com.lab.server_search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 分页的配置
 */
@Data
@Component
@ConfigurationProperties(value = "my-page")
public class PageSizeConfig {

    private Integer page;
    private Integer size;
}
