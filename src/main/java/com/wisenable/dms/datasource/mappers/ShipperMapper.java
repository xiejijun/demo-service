package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.Shipper;
import com.wisenable.dms.datasource.entities.ShipperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShipperMapper {
    long countByExample(ShipperExample example);

    int deleteByExample(ShipperExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Shipper record);

    int insertSelective(Shipper record);

    List<Shipper> selectByExample(ShipperExample example);

    Shipper selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Shipper record, @Param("example") ShipperExample example);

    int updateByExample(@Param("record") Shipper record, @Param("example") ShipperExample example);

    int updateByPrimaryKeySelective(Shipper record);

    int updateByPrimaryKey(Shipper record);

    List<Shipper> selectByTenantId();
}