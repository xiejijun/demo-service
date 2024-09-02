package com.wisenable.dms.service;

import com.wisenable.dms.utils.AnnotationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InterfaceContainer {
    private final Map<String, ICommonQuery> configComponentMap = new HashMap<>();

    @Autowired(required = false)
    private synchronized void init(ICommonQuery[] configComponents) {
        for (ICommonQuery configComponent : configComponents) {
            ResourceInfo info = AnnotationUtils.getAnnotation(configComponent, ResourceInfo.class);
            if (info != null) {
                configComponentMap.put(info.value(), configComponent);
            }
        }
    }

    public ICommonQuery getCommonQuery(String apiName) {
        return configComponentMap.get(apiName);
    }
}
