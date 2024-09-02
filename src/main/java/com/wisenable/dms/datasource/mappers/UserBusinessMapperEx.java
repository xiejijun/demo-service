package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.UserBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserBusinessMapperEx {

    int batchDeleteUserBusinessByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);

    List<UserBusiness> getBasicDataByKeyIdAndType(
            @Param("keyId") String keyId,
            @Param("type") String type);

    List<UserBusiness> getBasicDataByKeyIdsAndType(
            @Param("keyIds") List<String> keyId,
            @Param("type") String type);
}
