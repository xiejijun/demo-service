package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.MaterialExtend;
import com.wisenable.dms.datasource.vo.MaterialExtendVo4List;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialExtendMapperEx {

    int batchDeleteMaterialExtendByIds(@Param("ids") String ids[]);

    List<MaterialExtendVo4List> getDetailList(
            @Param("materialId") Long materialId);

    Long getMaxTimeByTenantAndTime(
            @Param("tenantId") Long tenantId,
            @Param("lastTime") Long lastTime,
            @Param("syncNum") Long syncNum);

    List<MaterialExtend> getListByMId(@Param("ids") Long ids[]);

    int batchDeleteMaterialExtendByMIds(@Param("ids") String ids[]);

    int specialUpdatePrice(MaterialExtend record);
}