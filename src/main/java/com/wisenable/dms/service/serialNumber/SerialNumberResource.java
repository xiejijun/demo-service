package com.wisenable.dms.service.serialNumber;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;


@ResourceInfo(value = "serialNumber")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialNumberResource {
}
