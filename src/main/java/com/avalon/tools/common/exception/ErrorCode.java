package com.avalon.tools.common.exception;

/**
 * 定义异常编码格式，用于确定返回客户端的异常编码和说明
 */
public interface ErrorCode {

    String getCode();

    String getMsg();

}
