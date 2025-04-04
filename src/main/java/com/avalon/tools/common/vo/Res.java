package com.avalon.tools.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Res<T> {
    /**
     * 响应编码
     */
    private String code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
}
