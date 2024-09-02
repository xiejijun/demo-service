package com.wisenable.dms.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception {
    private String msg;
    private int code;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(int code, String msg, Throwable throwable) {
        super(msg, throwable);
        this.code = code;
        this.msg = msg;
    }
}
