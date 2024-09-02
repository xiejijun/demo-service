package com.wisenable.dms.service.sequence;

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


@Service(value = "sequence_component")
@SequenceResource
public class SequenceComponent implements ICommonQuery {
    @Resource
    private SequenceService sequenceService;

    @Override
    public Object selectOne(Long id) throws Exception {
        return sequenceService.getSequence(id);
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getSequenceList(map);
    }

    private List<?> getSequenceList(Map<String, String> map) throws Exception{
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        return sequenceService.select(name,QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        return sequenceService.countSequence(name);
    }

    @Override
    public int insert(JSONObject obj, HttpServletRequest request)throws Exception {
        return sequenceService.insertSequence(obj, request);
    }

    @Override
    public int update(JSONObject obj, HttpServletRequest request)throws Exception {
        return sequenceService.updateSequence(obj, request);
    }

    @Override
    public int delete(Long id, HttpServletRequest request)throws Exception {
        return sequenceService.deleteSequence(id, request);
    }

    @Override
    public int deleteBatch(String ids, HttpServletRequest request)throws Exception {
        return sequenceService.batchDeleteSequence(ids, request);
    }

    @Override
    public int checkIsNameExist(Long id, String name) throws Exception{
        return sequenceService.checkIsNameExist(id, name);
    }
}
