package com.wisenable.dms.service.userBusiness;

import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.datasource.entities.User;
import com.wisenable.dms.datasource.entities.UserBusiness;
import com.wisenable.dms.datasource.entities.UserBusinessExample;
import com.wisenable.dms.datasource.mappers.UserBusinessMapper;
import com.wisenable.dms.datasource.mappers.UserBusinessMapperEx;
import com.wisenable.dms.exception.DMSException;
import com.wisenable.dms.service.log.LogService;
import com.wisenable.dms.service.user.UserService;
import com.wisenable.dms.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserBusinessService {
    private Logger logger = LoggerFactory.getLogger(UserBusinessService.class);

    @Resource
    private UserBusinessMapper userBusinessMapper;
    @Resource
    private UserBusinessMapperEx userBusinessMapperEx;
    @Resource
    private LogService logService;
    @Resource
    private UserService userService;

    public UserBusiness getUserBusiness(long id)throws Exception {
        UserBusiness result=null;
        try{
            result=userBusinessMapper.selectByPrimaryKey(id);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return result;
    }

    public List<UserBusiness> getUserBusiness()throws Exception {
        UserBusinessExample example = new UserBusinessExample();
        example.createCriteria().andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<UserBusiness> list=null;
        try{
            list=userBusinessMapper.selectByExample(example);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return list;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertUserBusiness(JSONObject obj, HttpServletRequest request) throws Exception {
        UserBusiness userBusiness = JSONObject.parseObject(obj.toJSONString(), UserBusiness.class);
        int result=0;
        try{
            String value = userBusiness.getValue();
            String newValue = value.replaceAll(",","\\]\\[");
            newValue = newValue.replaceAll("\\[0\\]","").replaceAll("\\[\\]","");
            userBusiness.setValue(newValue);
            result=userBusinessMapper.insertSelective(userBusiness);
            logService.insertLog("关联关系", BusinessConstants.LOG_OPERATION_TYPE_ADD, request);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUserBusiness(JSONObject obj, HttpServletRequest request) throws Exception {
        UserBusiness userBusiness = JSONObject.parseObject(obj.toJSONString(), UserBusiness.class);
        int result=0;
        try{
            String value = userBusiness.getValue();
            String newValue = value.replaceAll(",","\\]\\[");
            newValue = newValue.replaceAll("\\[0\\]","").replaceAll("\\[\\]","");
            userBusiness.setValue(newValue);
            result=userBusinessMapper.updateByPrimaryKeySelective(userBusiness);
            logService.insertLog("关联关系", BusinessConstants.LOG_OPERATION_TYPE_EDIT, request);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteUserBusiness(Long id, HttpServletRequest request)throws Exception {
        return batchDeleteUserBusinessByIds(id.toString());
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUserBusiness(String ids, HttpServletRequest request)throws Exception {
        return batchDeleteUserBusinessByIds(ids);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUserBusinessByIds(String ids) throws Exception{
        logService.insertLog("关联关系",
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        int result=0;
        try{
            result=  userBusinessMapperEx.batchDeleteUserBusinessByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    public int checkIsNameExist(Long id, String name)throws Exception {
        return 1;
    }

    public List<UserBusiness> getBasicData(String keyId, String type) {
        List<UserBusiness> list=null;
        try{
            list= userBusinessMapperEx.getBasicDataByKeyIdAndType(keyId, type);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public String getUBValueByTypeAndKeyId(String type, String keyId) {
        String ubValue = "";
        List<UserBusiness> ubList = getBasicData(keyId, type);
        if(ubList!=null && ubList.size()>0) {
            ubValue = ubList.get(0).getValue();
        }
        return ubValue;
    }

    public Set<String> getUBValueByTypeAndKeyIds(String type, List<String> keyIds) {
        Set<String> ubValues = new HashSet<>();
        List<UserBusiness> ubList = userBusinessMapperEx.getBasicDataByKeyIdsAndType(keyIds, type);
        if (ubList != null && ubList.size() > 0) {
            for (UserBusiness userBusiness : ubList) {
                ubValues.addAll(getValues(userBusiness.getValue()));
            }
        }
        return ubValues;
    }


    public Long checkIsValueExist(String type, String keyId) {
        UserBusinessExample example = new UserBusinessExample();
        example.createCriteria().andTypeEqualTo(type).andKeyIdEqualTo(keyId)
                .andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<UserBusiness> list=null;
        try{
            list= userBusinessMapper.selectByExample(example);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        Long id = null;
        if(list!=null&&list.size() > 0) {
            id = list.get(0).getId();
        }
        return id;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateBtnStr(String keyId, String type, String btnStr) throws Exception{
        logService.insertLog("关联关系",
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_EDIT).append("角色的按钮权限").toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setBtnStr(btnStr);
        UserBusinessExample example = new UserBusinessExample();
        example.createCriteria().andKeyIdEqualTo(keyId).andTypeEqualTo(type);
        int result=0;
        try{
            result=  userBusinessMapper.updateByExampleSelective(userBusiness, example);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    private Set<String> getValues(String value) {
        Set<String> values = new HashSet<>();
        if (Tools.isEmpty(value)) {
            return values;
        }

        value = value.replaceAll("\\[", ",").replaceAll("\\]", ",");
        String[] valueArray = value.split(",");
        for (String s : valueArray) {
            if (Tools.isEmpty(s)) {
                continue;
            }
            values.add(s);
        }
        return values;
    }
}
