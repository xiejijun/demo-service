package com.wisenable.dms.datasource.entities;

import lombok.Data;

import java.util.Date;

@Data
public class OrgaUserRel {
    private Long id;
    private Long orgaId;
    private Long userId;
    private String userBlngOrgaDsplSeq;
    private String deleteFlag;
    private Date createTime;
    private Long creator;
    private Date updateTime;
    private Long updater;
    private Long tenantId;
}