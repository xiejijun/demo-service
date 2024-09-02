package com.wisenable.dms.constants;

public enum PrescriptionEm {
    PURCHASE_ORDER(1);

    private Integer type;

    PrescriptionEm(int type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

}
