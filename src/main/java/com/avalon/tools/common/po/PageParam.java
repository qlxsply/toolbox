package com.avalon.tools.common.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class PageParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 页码，1表示第一页
     */
    private Integer page;
    /**
     * 每页结果数
     */
    private Integer size;
    /**
     * 排序
     */
    private Order[] orders;
}
