package com.wisenable.dms.api.vo;

import com.wisenable.dms.datasource.entities.Prescription;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseReq {
    private String orderNo;
    private String salesOrderNo;
    private Integer workType;
    private String salesName;
    private String salesOrderTime;
    private Integer productCount;
    private BigDecimal totalSalePrice;
    private BigDecimal totalAmount;
    private String remark;
    private Prescription prescription;
    private List<PurchaseDetailDTO> details;
}
