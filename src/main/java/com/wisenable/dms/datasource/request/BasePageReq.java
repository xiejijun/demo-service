package com.wisenable.dms.datasource.request;

import com.wisenable.dms.utils.PageQueryInfo;

public class BasePageReq {
    private Long pageSize;
    private Long pageNo;
    public long getPageSize() {
        if (pageSize == null || pageSize < 0) {
            pageSize = PageQueryInfo.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNo() {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1L;
        }
        return pageNo;
    }

    public long getRows() {
        if (pageSize == null || pageSize < 0) {
            return PageQueryInfo.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }
    public long getOffset() {
        if (pageNo == null || pageNo < 0) {
            return 0;
        }
        return (pageNo - 1) * getRows();
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }
}
