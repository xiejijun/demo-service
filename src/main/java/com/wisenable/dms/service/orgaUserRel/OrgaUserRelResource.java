package com.wisenable.dms.service.orgaUserRel;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;



@ResourceInfo(value = "orgaUserRel")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrgaUserRelResource {

}
