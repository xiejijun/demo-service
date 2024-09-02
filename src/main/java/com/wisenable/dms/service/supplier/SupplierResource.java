package com.wisenable.dms.service.supplier;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;


@ResourceInfo(value = "supplier")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupplierResource {
}
