package com.avalon.tools.common.exception;

import lombok.Getter;

/**
 * 系统内部异常，不对外展示，只用于内部服务定位问题。返回客户端时，使用关联业务编码进行展示
 */
@Getter
public class SysException extends RuntimeException {

    private final ErrorCode outwardCode;

    private SysException(ErrorCode outwardCode) {
        this.outwardCode = outwardCode;
    }

    public SysException(String message, ErrorCode outwardCode) {
        super(message);
        this.outwardCode = outwardCode;
    }

    public SysException(String message, Throwable cause, ErrorCode outwardCode) {
        super(message, cause);
        this.outwardCode = outwardCode;
    }

    public SysException(Throwable cause, ErrorCode outwardCode) {
        super(cause);
        this.outwardCode = outwardCode;
    }

    private SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
            ErrorCode outwardCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.outwardCode = outwardCode;
    }

}
