package com.avalon.tools.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Signal;

import java.util.Map;
import java.util.function.Consumer;

/**
 * author: avalon
 * date: 2025-04-02
 */
@Configuration
public class MdcContextConfig {

    @Bean
    public MDCContextLifter mdcContextLifter() {
        return new MDCContextLifter();
    }

    public static class MDCContextLifter implements Consumer<Signal<?>> {
        @Override
        public void accept(Signal<?> signal) {
            System.out.println("MDCContextLifter accept signal: " + signal.getType());
            switch (signal.getType()) {
                case ON_SUBSCRIBE, ON_NEXT -> MDC.setContextMap(signal.getContextView().getOrDefault("MDC", Map.of()));
                case ON_COMPLETE, ON_ERROR -> MDC.clear();
            }
        }
    }

}
