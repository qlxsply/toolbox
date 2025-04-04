package com.avalon.tools.common.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PageData<T> {
    /**
     * 总页数
     */
    private final Integer total;
    /**
     * 当前页码
     */
    private final Integer page;
    /**
     * 当前页元素数量
     */
    private final Integer num;
    /**
     * 当前页元素列表
     */
    private final List<T> list;

    /**
     * @param page  当前页码
     * @param size  每页数量
     * @param count 总元素数量
     * @param list  当前页元素列表
     */
    public PageData(Integer page, Integer size, Integer count, List<T> list) {
        this.page = page;
        this.list = list == null ? new ArrayList<>() : list;
        this.num = this.list.size();
        this.total = (count == null || count < 1) ? 0 : (((count - 1) / size) + 1);
    }
}
