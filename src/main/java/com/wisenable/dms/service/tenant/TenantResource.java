package com.wisenable.dms.service.tenant;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;


@ResourceInfo(value = "tenant")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantResource {
}
