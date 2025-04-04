package com.avalon.tools.common.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListData<T> {
    /**
     * 元素数量
     */
    private final Integer num;
    /**
     * 元素列表
     */
    private final List<T> list;

    /**
     * @param list 元素列表
     */
    public ListData(List<T> list) {
        if (list == null) {
            this.num = 0;
            this.list = new ArrayList<>();
            return;
        }
        this.num = list.size();
        this.list = list;
    }

}
