package com.avalon.tools.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "avalon")
public class AvalonProperties {

    private Trace trace = new Trace();
    private ThreadPool threadPool = new ThreadPool();

    @Getter
    @Setter
    public static class Trace {
        private Integer stackLimit = 20;
    }

    private static final int cpuNum = Runtime.getRuntime().availableProcessors();

    @Getter
    @Setter
    public static class ThreadPool {
        private Integer corePoolSize = cpuNum * 2;
        private Integer maxPoolSize = cpuNum * 2;
        private Integer queueCapacity = 200;
        private Integer keepAliveTime = 60;
    }

}
