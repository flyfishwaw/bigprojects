package com.waw.ipservice.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author flyfish
 * @date 2020-08-21 14:43:01
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "ip.check")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class IpCheckConfig {
    private int times;
    private String url;
    private int timeout;
    private int threadCount;
}
