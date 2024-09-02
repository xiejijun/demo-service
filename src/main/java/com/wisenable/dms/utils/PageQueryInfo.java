package com.wisenable.dms.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageQueryInfo<T> {
    private Long totalRows;
    private List<T> rows;
    private Long pageNo;
    private Long pageSize;

    public Integer code;
    public Object obj;

    public static final long DEFAULT_PAGE_SIZE = 10L;

    public static <T> PageQueryInfo<T> empty() {
        return ok(0L, DEFAULT_PAGE_SIZE, 0L, null);
    }

    public static <T> PageQueryInfo<T> failed() {
        return failed(500, 0L, DEFAULT_PAGE_SIZE);
    }

    public static <T> PageQueryInfo<T> ok(Long pageNo, Long pageSize, Long totalRows, List<T> rows) {
        PageQueryInfo<T> pageQueryInfo = new PageQueryInfo<>();
        pageQueryInfo.code = 200;
        pageQueryInfo.pageNo = pageNo;
        pageQueryInfo.pageSize = pageSize;
        pageQueryInfo.totalRows = totalRows;
        pageQueryInfo.rows = rows;
        return pageQueryInfo;
    }

    public static <T> PageQueryInfo<T> failed(Integer code, Long pageNo, Long pageSize) {
        PageQueryInfo<T> pageQueryInfo = new PageQueryInfo<>();
        pageQueryInfo.code = code;
        pageQueryInfo.pageNo = pageNo;
        pageQueryInfo.pageSize = pageSize;
        pageQueryInfo.totalRows = 0L;
        return pageQueryInfo;
    }

}
