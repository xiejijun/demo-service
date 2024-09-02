package com.wisenable.dms.datasource.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PurchaseUpdateReq {
    private Long id;

    @ApiModelProperty(value = "采购单状态")
    private Integer status;

    @ApiModelProperty(value = "快递单号")
    private String trackingNo;

    @ApiModelProperty(value = "内部系统单号")
    private String thirdNo;
}
