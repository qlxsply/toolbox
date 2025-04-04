package com.avalon.tools.util;

import com.avalon.tools.common.exception.BaseError;
import com.avalon.tools.common.exception.ErrorCode;
import com.avalon.tools.common.solid.TraceConst;
import com.avalon.tools.common.vo.Res;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ResUtil {

    public static <T> Mono<ResponseEntity<Res<T>>> success(T data) {
        return Mono.deferContextual(ctx -> {
            Map<String, String> mdc = ctx.get(TraceConst.MDC);

            HttpHeaders headers = new HttpHeaders();
            headers.add(TraceConst.TRACE_ID, mdc.get(TraceConst.TRACE_ID));

            return Mono.just(new ResponseEntity<>(successRes(data), headers, HttpStatus.OK));
        });
    }

    public static <T> Mono<ResponseEntity<Res<T>>> fail(ErrorCode code) {
        return Mono.deferContextual(ctx -> {
            Map<String, String> mdc = ctx.get(TraceConst.MDC);

            HttpHeaders headers = new HttpHeaders();
            headers.add(TraceConst.TRACE_ID, mdc.get(TraceConst.TRACE_ID));

            return Mono.just(new ResponseEntity<>(failRes(code), headers, HttpStatus.OK));
        });
    }

    private static <T> Res<T> successRes(T data) {
        Res<T> res = new Res<>();
        res.setCode(BaseError.SUCCESS.getCode());
        res.setMsg(BaseError.SUCCESS.getMsg());
        res.setData(data);
        return res;
    }

    private static <T> Res<T> failRes(ErrorCode code) {
        Res<T> res = new Res<>();
        res.setCode(code.getCode());
        res.setMsg(code.getMsg());
        res.setData(null);
        return res;
    }

}
