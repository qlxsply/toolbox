package com.avalon.tools.common.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 排序的字段
     */
    private String field;
    /**
     * 排序方式（正序还是反序）
     */
    private Direction direction;
}
