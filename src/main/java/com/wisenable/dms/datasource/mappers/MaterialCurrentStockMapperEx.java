package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.MaterialCurrentStock;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MaterialCurrentStockMapperEx {

    int batchInsert(List<MaterialCurrentStock> list);

    List<MaterialCurrentStock> getCurrentStockMapByIdList(
            @Param("materialIdList") List<Long> materialIdList);

    void updateUnitPriceByMId(
            @Param("currentUnitPrice") BigDecimal currentUnitPrice,
            @Param("materialId") Long materialId);
}