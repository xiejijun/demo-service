package com.wisenable.dms.constants;

import java.util.Objects;

public enum WorkTypeEm {
    NO_WORK(0, "否"),
    WORk(1, "是");

    private Integer workType;
    private String workTypeStr;

    WorkTypeEm(Integer workType, String workTypeStr) {
        this.workType = workType;
        this.workTypeStr = workTypeStr;
    }

    public Integer getWorkType() {
        return workType;
    }
    public String getWorkTypeStr() {
        return workTypeStr;
    }

    public static String getWorkTypeStr(Integer workType) {
        for (WorkTypeEm value : values()) {
            if (Objects.equals(value.workType, workType)) {
                return value.workTypeStr;
            }
        }
        return "";
    }
}
