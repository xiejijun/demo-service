package com.wisenable.dms.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseDetailDTO {
    private String category;
    private String productName;
    private String degree;
    private String sph;
    private String cyl;
    private Integer productCount;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String remark;
    private String brandName;
    private String productCode;
}
