package com.avalon.tools.common.exception;

/**
 * 业务异常，用于返回客户端说明业务失败原因
 */
public class BizException extends RuntimeException {

    private final ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BizException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    private BizException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    private BizException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    private BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public ErrorCode getBizCode() {
        return this.errorCode;
    }

}
