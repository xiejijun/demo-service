package com.wisenable.dms.api.vo;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderMsgVO {
    private String orderType;
    private String orderTime;
    private String customerName;
    private String orderNo;
    private String linkUrl;
    private List<String> openIds;
}
