package com.wisenable.dms.datasource.mappers;

import com.wisenable.dms.datasource.entities.SerialNumberEx;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SerialNumberMapperEx {

    int addSerialNumber(SerialNumberEx serialNumberEx);

    int updateSerialNumber(SerialNumberEx serialNumberEx);

    int findSerialNumberByMaterialId(@Param("materialId") Long materialId);

    int sellSerialNumber(@Param("materialId")Long materialId, @Param("outBillNo")String outBillNo, @Param("snArray") String snArray[], @Param("updateTime") Date updateTime,@Param("updater") Long updater);

    int cancelSerialNumber(@Param("materialId")Long materialId, @Param("outBillNo")String outBillNo, @Param("count")Integer count, @Param("updateTime") Date updateTime,@Param("updater") Long updater);

    int batAddSerialNumber(@Param("list") List<SerialNumberEx> list);

    int batchDeleteSerialNumberByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);

    List<SerialNumberEx> getEnableSerialNumberList(@Param("number") String number,
                                                 @Param("name") String name,
                                                 @Param("depotId") Long depotId,
                                                 @Param("barCode") String barCode,
                                                 @Param("offset") Integer offset, @Param("rows") Integer rows);

    Long getEnableSerialNumberCount(@Param("number") String number,
                                    @Param("name") String name,
                                    @Param("depotId") Long depotId,
                                    @Param("barCode") String barCode);

    int getIsNotSellCountByParam(
            @Param("materialId") Long materialId,
            @Param("serialNumber") String serialNumber);
}
