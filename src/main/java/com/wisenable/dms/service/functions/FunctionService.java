package com.wisenable.dms.service.functions;

import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.datasource.entities.*;
import com.wisenable.dms.datasource.mappers.FunctionMapper;
import com.wisenable.dms.datasource.mappers.FunctionMapperEx;
import com.wisenable.dms.exception.DMSException;
import com.wisenable.dms.service.log.LogService;
import com.wisenable.dms.service.user.UserService;
import com.wisenable.dms.service.userBusiness.UserBusinessService;
import com.wisenable.dms.utils.StringUtil;
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
import java.util.stream.Collectors;

@Service
public class FunctionService {
    private final Logger logger = LoggerFactory.getLogger(FunctionService.class);

    @Resource
    private FunctionMapper functionsMapper;

    @Resource
    private FunctionMapperEx functionMapperEx;
    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @Resource
    private UserBusinessService userBusinessService;

    public Function getFunction(long id) {
        Function result = null;
        try {
            result = functionsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return result;
    }

    public List<Function> getFunctionListByIds(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        List<Function> list = new ArrayList<>();
        try {
            FunctionExample example = new FunctionExample();
            example.createCriteria().andIdIn(idList);
            list = functionsMapper.selectByExample(example);
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public List<Function> getFunction() throws Exception {
        FunctionExample example = new FunctionExample();
        example.createCriteria().andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Function> list = null;
        try {
            list = functionsMapper.selectByExample(example);
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public List<FunctionEx> select(String name, String type, int offset, int rows) throws Exception {
        List<FunctionEx> list = null;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                list = functionMapperEx.selectByConditionFunction(name, type, offset, rows);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public Long countFunction(String name, String type) throws Exception {
        Long result = null;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = functionMapperEx.countsByFunction(name, type);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertFunction(JSONObject obj, HttpServletRequest request) {
        Function functions = JSONObject.parseObject(obj.toJSONString(), Function.class);
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                functions.setState(false);
                functions.setType("电脑版");
                result = functionsMapper.insertSelective(functions);
                logService.insertLog("功能",
                        new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_ADD).append(functions.getName()).toString(), request);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateFunction(JSONObject obj, HttpServletRequest request) {
        Function functions = JSONObject.parseObject(obj.toJSONString(), Function.class);
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = functionsMapper.updateByPrimaryKeySelective(functions);
                logService.insertLog("功能",
                        BusinessConstants.LOG_OPERATION_TYPE_EDIT + functions.getName(), request);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteFunction(Long id, HttpServletRequest request) throws Exception {
        return batchDeleteFunctionByIds(id.toString());
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteFunction(String ids, HttpServletRequest request) throws Exception {
        return batchDeleteFunctionByIds(ids);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteFunctionByIds(String ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(BusinessConstants.LOG_OPERATION_TYPE_DELETE);
        List<Function> list = getFunctionListByIds(ids);
        for (Function functions : list) {
            sb.append("[").append(functions.getName()).append("]");
        }
        User userInfo = userService.getCurrentUser();
        String[] idArray = ids.split(",");
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = functionMapperEx.batchDeleteFunctionByIds(new Date(), userInfo == null ? null : userInfo.getId(), idArray);
                logService.insertLog("功能", sb.toString(),
                        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    public int checkIsNameExist(Long id, String name) throws Exception {
        FunctionExample example = new FunctionExample();
        example.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Function> list = null;
        try {
            list = functionsMapper.selectByExample(example);
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list == null ? 0 : list.size();
    }

    public int checkIsNumberExist(Long id, String number) {
        FunctionExample example = new FunctionExample();
        example.createCriteria().andIdNotEqualTo(id).andNumberEqualTo(number).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Function> list = null;
        try {
            list = functionsMapper.selectByExample(example);
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list == null ? 0 : list.size();
    }

    public List<Function> findRoleFunction(String pnumber, boolean approvalFlag, Map<String, List<Function>> treeMap) {
        List<Function> list = new ArrayList<>();
        try {
            List<Function> tmplist = treeMap.get(pnumber);
            if (Tools.isNotEmpty(tmplist)) {
                for (Function function : tmplist) {
                    if ("0".equals(pnumber) && !approvalFlag && "/workflow".equalsIgnoreCase(function.getUrl())) {
                        continue;
                    }
                    list.add(function);
                }
            }

        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public List<Function> findByIds(String functionsIds) {
        List<Long> idList = StringUtil.strToLongList(functionsIds);
        FunctionExample example = new FunctionExample();
        example.createCriteria().andEnabledEqualTo(true).andIdIn(idList).andPushBtnIsNotNull().andPushBtnNotEqualTo("")
                .andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        example.setOrderByClause("Sort asc");
        List<Function> list = null;
        try {
            list = functionsMapper.selectByExample(example);
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public Map<String, List<Function>> buildTree() {
        FunctionExample example = new FunctionExample();
        example.createCriteria().andEnabledEqualTo(true)
                .andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        example.setOrderByClause("Sort");
        List<Function> all = functionsMapper.selectByExample(example);
        return all.stream().collect(Collectors.groupingBy(Function::getParentNumber, Collectors.toList()));
    }


    public Map<String, List<Function>> buildTreeWithTenant(Long tenantId) {
        if (tenantId == null || tenantId == 0) {
            return buildTree();
        }

        String userRole = userBusinessService.getUBValueByTypeAndKeyId("UserRole", String.valueOf(tenantId));
        List<String> roleIds = getValues(userRole);
        if (Tools.isEmpty(roleIds)) {
            return new HashMap<>();
        }

        Set<String> functionIds = userBusinessService.getUBValueByTypeAndKeyIds("RoleFunctions", roleIds);
        if (functionIds == null || functionIds.size() == 0) {
            return new HashMap<>();
        }
        List<Long> ids = new ArrayList<>();
        for (String functionId : functionIds) {
            ids.add(Long.valueOf(functionId));
        }

        FunctionExample example = new FunctionExample();
        example.createCriteria().andEnabledEqualTo(true)
                .andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED).andIdIn(ids);
        example.setOrderByClause("Sort");
        List<Function> all = functionsMapper.selectByExample(example);
        return all.stream().collect(Collectors.groupingBy(Function::getParentNumber, Collectors.toList()));
    }

    private List<String> getValues(String value) {
        List<String> values = new ArrayList<>();
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
