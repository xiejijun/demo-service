package com.wisenable.dms.utils;

import lombok.Data;

import java.util.List;

@Data
public class BaseResponseList<T> {
    private int code;
    private List<T> data;
    private String msg;

    public static <T> BaseResponseList<T> ok(List<T> data) {
        BaseResponseList<T> baseResponseList = new BaseResponseList<>();
        baseResponseList.code = 200;
        baseResponseList.msg = "SUCCESS";
        baseResponseList.setData(data);
        return baseResponseList;
    }

    public static <T> BaseResponseList<T> error(int code, String msg) {
        BaseResponseList<T> baseResponseList = new BaseResponseList<>();
        baseResponseList.code = code;
        baseResponseList.msg = msg;
        baseResponseList.setData(null);
        return baseResponseList;
    }
}
