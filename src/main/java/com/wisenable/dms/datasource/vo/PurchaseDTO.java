package com.wisenable.dms.datasource.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PurchaseDTO {
    private Long id;
    private String orderNo;
    private Integer orderStatus;
    private Date orderTime;
    private String trackingNo;
    private Integer workType;
    private String thirdNo;
    private Long shipperId;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Long tenantId;
    private Long auditId;
    private String category;
    private String productName;
    private String brandName;
    private String productInfo;
    private String sph;
    private String cyl;
    private String degree;
    private Integer productCount;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String productRemark;
}
