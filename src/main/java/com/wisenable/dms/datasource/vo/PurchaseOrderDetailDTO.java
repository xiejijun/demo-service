package com.wisenable.dms.datasource.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseOrderDetailDTO {
    private Long id;
    private String category;
    private String productName;
    private String productInfo;
    private String degree;
    private Integer productCount;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String productRemark;
}
