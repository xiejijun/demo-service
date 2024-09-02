package com.wisenable.dms.service.organization;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;



@ResourceInfo(value = "organization")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrganizationResource {
}
