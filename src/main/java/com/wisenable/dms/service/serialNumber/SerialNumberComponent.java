package com.wisenable.dms.service.serialNumber;

import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.service.ICommonQuery;
import com.wisenable.dms.utils.Constants;
import com.wisenable.dms.utils.QueryUtils;
import com.wisenable.dms.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Service(value = "serialNumber_component")
@SerialNumberResource
public class SerialNumberComponent implements ICommonQuery {
    @Resource
    private SerialNumberService serialNumberService;

    @Override
    public Object selectOne(Long id) throws Exception {
        return serialNumberService.getSerialNumber(id);
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getSerialNumberList(map);
    }

    private List<?> getSerialNumberList(Map<String, String> map) throws Exception{
        String search = map.get(Constants.SEARCH);
        String serialNumber = StringUtil.getInfo(search, "serialNumber");
        String materialName = StringUtil.getInfo(search, "materialName");
        return serialNumberService.select(serialNumber,materialName,QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String serialNumber = StringUtil.getInfo(search, "serialNumber");
        String materialName = StringUtil.getInfo(search, "materialName");
        return serialNumberService.countSerialNumber(serialNumber, materialName);
    }

    @Override
    public int insert(JSONObject obj, HttpServletRequest request)throws Exception {
        return serialNumberService.insertSerialNumber(obj, request);
    }

    @Override
    public int update(JSONObject obj, HttpServletRequest request)throws Exception {
        return serialNumberService.updateSerialNumber(obj, request);
    }

    @Override
    public int delete(Long id, HttpServletRequest request)throws Exception {
        return serialNumberService.deleteSerialNumber(id, request);
    }

    @Override
    public int deleteBatch(String ids, HttpServletRequest request)throws Exception {
        return serialNumberService.batchDeleteSerialNumber(ids, request);
    }

    @Override
    public int checkIsNameExist(Long id, String serialNumber) throws Exception{
        return serialNumberService.checkIsNameExist(id, serialNumber);
    }
}
