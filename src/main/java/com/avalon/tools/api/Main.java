package com.avalon.tools.api;

import com.avalon.tools.common.solid.TraceConst;
import com.avalon.tools.common.vo.Res;
import com.avalon.tools.util.ResUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * author: avalon
 * date: 2025-03-16
 */
@Slf4j
@RestController
@RequestMapping("main")
public class Main {

    @GetMapping("test")
    public Mono<ResponseEntity<Res<String>>> list() {
        log.info("asdfasdfasdf");
        log.info("Current traceId: {}", MDC.get(TraceConst.TRACE_ID)); // 应能正确获取

        return ResUtil.success("12341234");
    }

}
