package com.wisenable.dms.service.log;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.datasource.entities.Log;
import com.wisenable.dms.datasource.entities.LogExample;
import com.wisenable.dms.datasource.entities.Shipper;
import com.wisenable.dms.datasource.mappers.LogMapper;
import com.wisenable.dms.datasource.mappers.LogMapperEx;
import com.wisenable.dms.datasource.vo.LogVo4List;
import com.wisenable.dms.exception.DMSException;
import com.wisenable.dms.service.redis.RedisService;
import com.wisenable.dms.service.user.UserService;
import com.wisenable.dms.utils.StringUtil;
import com.wisenable.dms.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.wisenable.dms.utils.Tools.getLocalIp;

@Service
public class LogService {
    private final Logger logger = LoggerFactory.getLogger(LogService.class);
    @Resource
    private LogMapper logMapper;

    @Resource
    private LogMapperEx logMapperEx;

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    public Log getLog(long id)throws Exception {
        Log result=null;
        try{
            result=logMapper.selectByPrimaryKey(id);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return result;
    }

    public List<Log> getLog()throws Exception {
        LogExample example = new LogExample();
        List<Log> list=null;
        try{
            list=logMapper.selectByExample(example);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public List<LogVo4List> select(String operation, String userInfo, String clientIp, String tenantLoginName, String tenantType,
                                   String beginTime, String endTime, String content, int offset, int rows)throws Exception {
        List<LogVo4List> list=null;
        try{
            beginTime = Tools.parseDayToTime(beginTime,BusinessConstants.DAY_FIRST_TIME);
            endTime = Tools.parseDayToTime(endTime,BusinessConstants.DAY_LAST_TIME);
            list=logMapperEx.selectByConditionLog(operation, userInfo, clientIp, tenantLoginName, tenantType, beginTime, endTime,
                    content, offset, rows);
            if (null != list) {
                for (LogVo4List log : list) {
                    log.setCreateTimeStr(Tools.getCenternTime(log.getCreateTime()));
                }
            }
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public Long countLog(String operation, String userInfo, String clientIp, String tenantLoginName, String tenantType,
                         String beginTime, String endTime, String content) {
        Long result=null;
        try{
            beginTime = Tools.parseDayToTime(beginTime,BusinessConstants.DAY_FIRST_TIME);
            endTime = Tools.parseDayToTime(endTime,BusinessConstants.DAY_LAST_TIME);
            result=logMapperEx.countsByLog(operation, userInfo, clientIp, tenantLoginName, tenantType, beginTime, endTime, content);
        }catch(Exception e){
            DMSException.readFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertLog(JSONObject obj, HttpServletRequest request) throws Exception {
        Log log = JSONObject.parseObject(obj.toJSONString(), Log.class);
        int result=0;
        try {
            result=logMapper.insertSelective(log);
        } catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateLog(JSONObject obj, HttpServletRequest request) {
        Log log = JSONObject.parseObject(obj.toJSONString(), Log.class);
        int result=0;
        try{
            result=logMapper.updateByPrimaryKeySelective(log);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteLog(Long id, HttpServletRequest request) {
        int result=0;
        try{
            result=logMapper.deleteByPrimaryKey(id);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteLog(String ids, HttpServletRequest request) {
        List<Long> idList = StringUtil.strToLongList(ids);
        LogExample example = new LogExample();
        example.createCriteria().andIdIn(idList);
        int result=0;
        try{
            result=logMapper.deleteByExample(example);
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    public void insertLog(String moduleName, String content, HttpServletRequest request) {
        try{
            Long userId = userService.getUserId(request);
            if(userId!=null) {
                String clientIp = getLocalIp(request);
                String createTime = Tools.getNow3();
                Long count = logMapperEx.getCountByIpAndDate(userId, moduleName, clientIp, createTime);
                if(count > 0) {
                    //如果某个用户某个IP在同1秒内连续操作两遍，此时需要删除该redis记录，使其退出，防止恶意攻击
                    redisService.deleteObjectByUserAndIp(userId, clientIp);
                } else {
                    Log log = new Log();
                    log.setUserId(userId);
                    log.setOperation(moduleName);
                    log.setClientIp(getLocalIp(request));
                    log.setCreateTime(new Date());
                    Byte status = 0;
                    log.setStatus(status);
                    log.setContent(content);
                    logMapper.insertSelective(log);
                }
            }
        } catch(Exception e){
            DMSException.writeFail(logger, e);
        }
    }

    public void insertApiLog(String moduleName, String content, Shipper shipper) {
        try {
            if (shipper != null) {
                String clientIp = "127.0.0.1";
                Log log = new Log();
                log.setUserId(shipper.getId());
                log.setOperation(moduleName);
                log.setClientIp(clientIp);
                log.setCreateTime(new Date());
                Byte status = 0;
                log.setStatus(status);
                log.setContent("[API]:" + content);
                log.setTenantId(shipper.getTenantId());
                logMapper.insertSelective(log);

            }
        } catch(Exception e){
            DMSException.writeFail(logger, e);
        }
    }

    public void insertLog(String moduleName, String operation, Object requestBody, HttpServletRequest request) {
        try {
            Long userId = userService.getUserId(request);

            String content = operation;
            if (requestBody != null) {
                content += ": " + JSONUtil.toJsonStr(requestBody);
            }

            if(userId!=null) {
                String clientIp = getLocalIp(request);
                Log log = new Log();
                log.setUserId(userId);
                log.setOperation(moduleName);
                log.setClientIp(clientIp);
                log.setCreateTime(new Date());
                Byte status = 0;
                log.setStatus(status);
                log.setContent(content);
                logMapper.insertSelective(log);

            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
    }

    public void insertLogWithUserId(Long userId, Long tenantId, String moduleName, String content, HttpServletRequest request)throws Exception{
        try{
            if(userId!=null) {
                Log log = new Log();
                log.setUserId(userId);
                log.setOperation(moduleName);
                log.setClientIp(getLocalIp(request));
                log.setCreateTime(new Date());
                Byte status = 0;
                log.setStatus(status);
                log.setContent(content);
                log.setTenantId(tenantId);
                logMapperEx.insertLogWithUserId(log);
            }
        }catch(Exception e){
            DMSException.writeFail(logger, e);
        }
    }
}
