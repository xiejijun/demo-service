package com.wisenable.dms.service.log;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;


@ResourceInfo(value = "log")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResource {
}
