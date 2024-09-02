package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.Depot;
import com.wisenable.dms.datasource.entities.DepotExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DepotMapper {
    long countByExample(DepotExample example);

    int deleteByExample(DepotExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    int insertSelective(Depot record);

    List<Depot> selectByExample(DepotExample example);

    Depot selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Depot record, @Param("example") DepotExample example);

    int updateByExample(@Param("record") Depot record, @Param("example") DepotExample example);

    int updateByPrimaryKeySelective(Depot record);

    int updateByPrimaryKey(Depot record);
}