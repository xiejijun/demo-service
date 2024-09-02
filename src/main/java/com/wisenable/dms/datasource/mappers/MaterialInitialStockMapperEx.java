package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.MaterialInitialStock;

import java.util.List;

public interface MaterialInitialStockMapperEx {

    int batchInsert(List<MaterialInitialStock> list);

    List<MaterialInitialStock> getListExceptZero();

}