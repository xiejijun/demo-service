package com.wisenable.dms.utils;

import lombok.Data;

@Data
public class BaseResponseObject<T> {
    private int code;
    private T data;
    private String msg;

    public static <T> BaseResponseObject<T> ok(T data) {
        BaseResponseObject<T> baseResponse = new BaseResponseObject<>();
        baseResponse.code = 200;
        baseResponse.msg = "SUCCESS";
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponseObject<T> error(int code, String msg) {
        BaseResponseObject<T> baseResponse = new BaseResponseObject<>();
        baseResponse.code = code;
        baseResponse.setData(null);
        baseResponse.msg = msg;
        return baseResponse;
    }
}
