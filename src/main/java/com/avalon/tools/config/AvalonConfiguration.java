package com.avalon.tools.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

/**
 * author: avalon
 * date: 2025-03-16
 */
@Configuration
@EnableConfigurationProperties(AvalonProperties.class)
public class AvalonConfiguration implements AsyncConfigurer {

    private final AvalonProperties avalonProperties;

    @Autowired
    public AvalonConfiguration(AvalonProperties properties) {
        this.avalonProperties = properties;
    }

}
