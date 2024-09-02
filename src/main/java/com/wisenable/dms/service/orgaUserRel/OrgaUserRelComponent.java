package com.wisenable.dms.service.orgaUserRel;

import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.service.ICommonQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



@Service(value = "orgaUserRel_component")
@OrgaUserRelResource
public class OrgaUserRelComponent implements ICommonQuery {
    @Resource
    private OrgaUserRelService orgaUserRelService;

    @Override
    public Object selectOne(Long id) throws Exception {
        return orgaUserRelService.getOrgaUserRel(id);
    }

    @Override
    public List<?> select(Map<String, String> parameterMap)throws Exception {
        return getOrgaUserRelList(parameterMap);
    }
    private List<?> getOrgaUserRelList(Map<String, String> map)throws Exception {
        return null;
    }
    @Override
    public Long counts(Map<String, String> parameterMap)throws Exception {
        return null;
    }

    @Override
    public int insert(JSONObject obj, HttpServletRequest request)throws Exception {
        return orgaUserRelService.insertOrgaUserRel(obj,request);
    }

    @Override
    public int update(JSONObject obj, HttpServletRequest request)throws Exception {
        return orgaUserRelService.updateOrgaUserRel(obj, request);
    }

    @Override
    public int delete(Long id, HttpServletRequest request)throws Exception {
        return orgaUserRelService.deleteOrgaUserRel(id, request);
    }

    @Override
    public int deleteBatch(String ids, HttpServletRequest request)throws Exception {
        return orgaUserRelService.batchDeleteOrgaUserRel(ids, request);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return 0;
    }
}
