package com.avalon.tools.common.exception;

public enum BaseError implements ErrorCode {
    SUCCESS("000000", "成功"),
    ERROR("000001", "系统故障，请联系管理员"),
    BUSY("000002", "系统繁忙，请稍后再试"),
    AUTH_FAILED("000003", "账号或密码错误"),
    INVALID_ACCESS_TOKEN("000004", "无效的访问令牌"),
    FORBIDDEN("000005", "权限不足，无法操作"),
    MISSING_PARAMETER("000006", "请求参数缺失"),
    INVALID_PARAMETER("000007", "请求参数格式错误"),
    ILLEGAL_ARGUMENT("000008", "请求参数无效"),
    DUPLICATE_REQUEST("000009", "重复提交请求"),
    FREQUENT("000010", "操作过于频繁，请稍后重试"),
    FILE_SIZE_EXCEEDS("000011", "文件大小超出限制"),
    FILE_TYPE_UNSUPPORTED("000012", "文件类型不支持"),
    THIRD_PARTY_ERROR("000013", "第三方服务异常"),
    INVALID_VERIFICATION_CODE("000014", "验证码错误"),
    USER_DISABLED("000015", "用户已禁用，请联系管理员"),
    ;

    private final String code;

    private final String msg;

    BaseError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
    
}
