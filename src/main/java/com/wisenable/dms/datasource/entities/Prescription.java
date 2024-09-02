package com.wisenable.dms.datasource.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Prescription implements Serializable {
    private Long id;
    private Long shipperId;
    private Integer type;
    private Long orderId;
    private Long tenantId;
    private String odSph;
    private String odCyl;
    private String odAxis;
    private String odVa;
    private String odPd;
    private String odAdd;
    private String odPh;
    private String odBc;
    private String osSph;
    private String osCyl;
    private String osAxis;
    private String osVa;
    private String osPd;
    private String osAdd;
    private String osPh;
    private String osBc;
    private String usage;
    private String dominantEye;
    private String remarks;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;
}