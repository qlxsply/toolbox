package com.avalon.tools.common.po;

import com.avalon.tools.common.exception.BaseError;
import com.avalon.tools.common.exception.BizException;

public enum Direction {
    /**
     * 升序
     */
    ASC,
    /**
     * 降序
     */
    DESC;

    public static Direction fromString(String value) throws IllegalArgumentException {
        //默认ASC
        if (value == null || value.trim().isEmpty()) {
            return ASC;
        }
        Direction[] values = Direction.values();
        for (Direction direction : values) {
            if (direction.name().equals(value.toUpperCase())) {
                return direction;
            }
        }
        String msg = String.format(
                "Invalid value [%s] for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value);
        throw new BizException(msg, BaseError.ILLEGAL_ARGUMENT);
    }

}
