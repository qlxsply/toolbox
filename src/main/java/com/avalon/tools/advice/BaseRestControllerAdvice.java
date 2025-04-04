package com.avalon.tools.advice;


import com.avalon.tools.common.exception.BaseError;
import com.avalon.tools.common.exception.BizException;
import com.avalon.tools.common.exception.SysException;
import com.avalon.tools.common.vo.Res;
import com.avalon.tools.config.AvalonProperties;
import com.avalon.tools.util.ResUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice(basePackages = "com.avalon")
public class BaseRestControllerAdvice {

    private int dept = 20;

    @Autowired
    public BaseRestControllerAdvice(AvalonProperties avalonProperties) {
        Integer stackLimit = avalonProperties.getTrace().getStackLimit();
        if (stackLimit == null || stackLimit < 1) {
            return;
        }
        this.dept = stackLimit;
    }

    @ExceptionHandler(BizException.class)
    public Mono<ResponseEntity<Res<Object>>> handleBizException(BizException e) {
        return ResUtil.fail(e.getBizCode());
    }

    @ExceptionHandler(SysException.class)
    public Mono<ResponseEntity<Res<Object>>> handleSysException(SysException e) {
        printStackTrace(e);
        return ResUtil.fail(e.getOutwardCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<Res<Object>>> handleRuntimeException(RuntimeException e) {
        printStackTrace(e);
        return ResUtil.fail(BaseError.ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Res<Object>>> handleException(Exception e) {
        printStackTrace(e);
        return ResUtil.fail(BaseError.ERROR);
    }

    /**
     * 打印异常堆栈信息
     *
     * @param e 异常
     */
    private void printStackTrace(Exception e) {
        if (e == null) {
            return;
        }
        int deptLimit = dept;
        Class<? extends Exception> eClass = e.getClass();
        log.error("{}: {}", eClass.getName(), e.getMessage());
        StackTraceElement[] st = e.getStackTrace();
        for (StackTraceElement ste : st) {
            if (deptLimit-- <= 0) {
                break;
            }
            String className = ste.getClassName();
            String methodName = ste.getMethodName();
            String fileName = ste.getFileName();
            int lineNumber = ste.getLineNumber();
            log.error(" at {}.{}({}:{})", className, methodName, fileName, lineNumber);
        }
    }

}
