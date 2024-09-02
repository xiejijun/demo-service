package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.PurchaseOrder;
import com.wisenable.dms.datasource.entities.PurchaseOrderExample;
import com.wisenable.dms.datasource.request.PurchaseListReq;
import com.wisenable.dms.datasource.vo.PurchaseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseOrderMapperEx {

    PurchaseOrder selectByOrderNo(@Param("orderNo") String orderNo, @Param("shipperId") Long shipperId);
}