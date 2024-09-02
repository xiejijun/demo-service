package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.PurchaseOrderDetail;
import com.wisenable.dms.datasource.entities.PurchaseOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurchaseOrderDetailMapper {
    long countByExample(PurchaseOrderDetailExample example);

    int deleteByExample(PurchaseOrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseOrderDetail record);

    int insertSelective(PurchaseOrderDetail record);

    List<PurchaseOrderDetail> selectByExample(PurchaseOrderDetailExample example);

    PurchaseOrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseOrderDetail record, @Param("example") PurchaseOrderDetailExample example);

    int updateByExample(@Param("record") PurchaseOrderDetail record, @Param("example") PurchaseOrderDetailExample example);

    int updateByPrimaryKeySelective(PurchaseOrderDetail record);

    int updateByPrimaryKey(PurchaseOrderDetail record);
}