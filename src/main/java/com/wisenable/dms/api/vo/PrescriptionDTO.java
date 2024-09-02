package com.wisenable.dms.api.vo;

import lombok.Data;

@Data
public class PrescriptionDTO {
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
}
