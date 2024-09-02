package com.wisenable.dms.datasource.vo;

import lombok.Data;

@Data
public class UserBindWistoreInfo {
    private Long userId;
    private String wistoreMsgOpenId;
    private String bindQRCode;
}
