package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.PurchaseOrder;
import com.wisenable.dms.datasource.entities.PurchaseOrderExample;
import java.util.List;
import java.util.Map;

import com.wisenable.dms.datasource.request.PurchaseListReq;
import com.wisenable.dms.datasource.vo.PurchaseDTO;
import org.apache.ibatis.annotations.Param;

public interface PurchaseOrderMapper {
    long countByExample(PurchaseOrderExample example);

    int deleteByExample(PurchaseOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    List<PurchaseOrder> selectByExampleWithBLOBs(PurchaseOrderExample example);

    List<PurchaseOrder> selectByExample(PurchaseOrderExample example);

    PurchaseOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderExample example);

    int updateByExampleWithBLOBs(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderExample example);

    int updateByExample(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderExample example);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKeyWithBLOBs(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);

     List<PurchaseDTO> getPurchaseList(@Param("offset") long offset, @Param("rows") long rows, @Param("req") PurchaseListReq req);
     int countPurchaseList(@Param("req") PurchaseListReq req);

     List<Map<String, Object>> sumPurchase(@Param("req") PurchaseListReq req);

}