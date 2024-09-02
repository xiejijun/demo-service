package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.OrgaUserRel;
import com.wisenable.dms.datasource.entities.OrgaUserRelExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrgaUserRelMapper {

    int countByExample(OrgaUserRelExample example);

    int deleteByExample(OrgaUserRelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrgaUserRel record);

    int insertSelective(OrgaUserRel record);

    List<OrgaUserRel> selectByExample(OrgaUserRelExample example);

    OrgaUserRel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrgaUserRel record, @Param("example") OrgaUserRelExample example);

    int updateByExample(@Param("record") OrgaUserRel record, @Param("example") OrgaUserRelExample example);

    int updateByPrimaryKeySelective(OrgaUserRel record);

    int updateByPrimaryKey(OrgaUserRel record);
}