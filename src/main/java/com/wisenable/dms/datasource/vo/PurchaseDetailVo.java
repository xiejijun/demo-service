package com.wisenable.dms.datasource.vo;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDetailVo {
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
    private Long auditUser;
    private String auditName;

    private List<PurchaseOrderDetailDTO> details;
    private List<PrescriptionVO> prescriptions;
}
