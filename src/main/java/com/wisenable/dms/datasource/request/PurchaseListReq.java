package com.wisenable.dms.datasource.request;

import lombok.Data;

@Data
public class PurchaseListReq extends BasePageReq {
    private String orderNo;
    private Integer orderStatus;
    private Long shipperId;
    private Long startTime;
    private Long endTime;
    private Long id;
    private String productName;
    private Long auditId;
    private String remark;
}
