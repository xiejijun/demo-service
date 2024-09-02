package com.wisenable.dms.service.sequence;

import com.wisenable.dms.service.ResourceInfo;

import java.lang.annotation.*;


@ResourceInfo(value = "sequence")
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SequenceResource {
}
