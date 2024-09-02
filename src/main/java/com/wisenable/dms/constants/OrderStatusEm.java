package com.wisenable.dms.constants;

import java.util.Objects;

public enum OrderStatusEm {
    CANCELED(-1, "待接单"),
    PENDING(0, "待接单"),
    RECEIVED(1, "待发货"),
    DESPATCHED(2, "已发货"),
    DELIVERED(3, "已收货"); // 0:待接单、1:待发货、2:已发货、3:已收货'

    private Integer status;
    private String cnName;

    OrderStatusEm(Integer status, String cnName) {
        this.status = status;
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

    public Integer getStatus() {
        return status;
    }

    public static String mapCnName(Integer status) {
        for (OrderStatusEm value : values()) {
            if (Objects.equals(value.status, status)) {
                return value.cnName;
            }
        }
        return "";
    }

    public static OrderStatusEm status(Integer statusCode) {
        for (OrderStatusEm value : values()) {
            if (Objects.equals(value.getStatus(), statusCode)) {
                return value;
            }
        }
        return null;
    }

    public static String getAction(OrderStatusEm em) {
        if (em == null) {
            return "UnKnown";
        }
        switch (em) {
            case RECEIVED:
                return "接单";
            case DESPATCHED:
                return "发货";
            case DELIVERED:
                return "确认收货";
        }
        return "UnKnown";
    }

    public static String getRollAction(OrderStatusEm em) {
        if (em == null) {
            return "UnKnown Cancel";
        }
        switch (em) {
            case PENDING:
                return "取消接单";
            case RECEIVED:
                return "取消发货";
            case DESPATCHED:
                return "取消收货";
        }
        return "UnKnown Cancel";
    }
}
