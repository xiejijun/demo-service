package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.AccountItem;
import com.wisenable.dms.datasource.entities.AccountItemExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AccountItemMapper {
    long countByExample(AccountItemExample example);

    int deleteByExample(AccountItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountItem record);

    int insertSelective(AccountItem record);

    List<AccountItem> selectByExample(AccountItemExample example);

    AccountItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountItem record, @Param("example") AccountItemExample example);

    int updateByExample(@Param("record") AccountItem record, @Param("example") AccountItemExample example);

    int updateByPrimaryKeySelective(AccountItem record);

    int updateByPrimaryKey(AccountItem record);
}