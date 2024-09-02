package com.wisenable.dms.api.vo;

import lombok.Data;

@Data
public class PurchaseUpdateStatusReq {
    private String orderNo;
    private Integer status;
    private String thirdNo;
    private String trackingNo;
    private String remark;
}
