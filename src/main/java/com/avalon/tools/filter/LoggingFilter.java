package com.avalon.tools.filter;


import com.avalon.tools.common.solid.TraceConst;
import com.avalon.tools.util.id.IdUtil;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;


/**
 * author: avalon
 * date: 2025-04-01
 */
@Order(1)
@Component
public class LoggingFilter implements WebFilter {

    /**
     * @param exchange 封装了 HTTP 请求和响应的上下文对象
     * @param chain    过滤器链，用于调用下一个 WebFilter 或目标处理器
     */
    @Override
    public @NonNull Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        return Mono.deferContextual(contextView -> {
            // 生成追踪ID
            String traceId = IdUtil.random32();
            String spanId = IdUtil.random32();
            Map<String, String> traceInfo = Map.of(TraceConst.TRACE_ID, traceId, TraceConst.SPAN_ID, spanId);

            // 先写入 Context，再执行 filter
//            return chain.filter(exchange).contextWrite(Context.of(TraceConst.MDC, traceInfo)).doOnEach(signal -> {
//                MDC.setContextMap(signal.getContextView().getOrDefault(TraceConst.MDC, Map.of()));
////                if (signal.isOnNext() || signal.isOnError()) {
////
////                }
//            });
            return chain.filter(exchange).contextWrite(Context.of(TraceConst.MDC, traceInfo));
        });
    }

}
