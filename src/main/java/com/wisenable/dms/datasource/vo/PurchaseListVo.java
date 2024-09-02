package com.wisenable.dms.datasource.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseListVo {
    private Long id;
    private String orderNo;
    private Integer orderStatus;
    private String orderStatusCn;
    private String orderTime;
    private String trackingNo;
    private Integer workType;
    private String workTypeCn;
    private String thirdNo;
    private Long shipperId;
    private String shipperName;
    private String remark;
    private Long createTime;
    private Long updateTime;
    private Long tenantId;
    private Long auditId;
    private String auditUser;
    private String category;
    private String productName;
    private String productInfo;
    private String degree;
    private Integer productCount;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String productRemark;
}
