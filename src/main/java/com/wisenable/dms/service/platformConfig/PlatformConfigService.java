package com.wisenable.dms.service.platformConfig;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.datasource.entities.PlatformConfig;
import com.wisenable.dms.datasource.entities.PlatformConfigExample;
import com.wisenable.dms.datasource.mappers.PlatformConfigMapper;
import com.wisenable.dms.datasource.mappers.PlatformConfigMapperEx;
import com.wisenable.dms.datasource.vo.PlatformInfoVO;
import com.wisenable.dms.exception.DMSException;
import com.wisenable.dms.service.user.UserService;
import com.wisenable.dms.utils.StringUtil;
import com.wisenable.dms.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PlatformConfigService {
    private final Logger logger = LoggerFactory.getLogger(PlatformConfigService.class);

    @Resource
    private UserService userService;

    @Resource
    private PlatformConfigMapper platformConfigMapper;

    @Resource
    private PlatformConfigMapperEx platformConfigMapperEx;

    public PlatformConfig getPlatformConfig(long id) {
        PlatformConfig result = null;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = platformConfigMapper.selectByPrimaryKey(id);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return result;
    }

    public List<PlatformConfig> getPlatformConfig() throws Exception {
        PlatformConfigExample example = new PlatformConfigExample();
        example.createCriteria();
        List<PlatformConfig> list = null;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                list = platformConfigMapper.selectByExample(example);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public List<PlatformConfig> select(String platformKey, int offset, int rows) throws Exception {
        List<PlatformConfig> list = null;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                list = platformConfigMapperEx.selectByConditionPlatformConfig(platformKey, offset, rows);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return list;
    }

    public Long countPlatformConfig(String platformKey) {
        Long result = null;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = platformConfigMapperEx.countsByPlatformConfig(platformKey);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertPlatformConfig(JSONObject obj, HttpServletRequest request) {
        PlatformConfig platformConfig = JSONObject.parseObject(obj.toJSONString(), PlatformConfig.class);
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = platformConfigMapper.insertSelective(platformConfig);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updatePlatformConfig(JSONObject obj, HttpServletRequest request) throws Exception {
        PlatformConfig platformConfig = JSONObject.parseObject(obj.toJSONString(), PlatformConfig.class);
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = platformConfigMapper.updateByPrimaryKeySelective(platformConfig);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deletePlatformConfig(Long id, HttpServletRequest request) {
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = platformConfigMapper.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeletePlatformConfig(String ids, HttpServletRequest request) {
        List<Long> idList = StringUtil.strToLongList(ids);
        PlatformConfigExample example = new PlatformConfigExample();
        example.createCriteria().andIdIn(idList);
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                result = platformConfigMapper.deleteByExample(example);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    public int updatePlatformConfigByKey(String platformKey, String platformValue) throws Exception {
        int result = 0;
        try {
            if (BusinessConstants.DEFAULT_MANAGER.equals(userService.getCurrentUser().getLoginName())) {
                PlatformConfig platformConfig = new PlatformConfig();
                platformConfig.setPlatformValue(platformValue);
                PlatformConfigExample example = new PlatformConfigExample();
                example.createCriteria().andPlatformKeyEqualTo(platformKey);
                result = platformConfigMapper.updateByExampleSelective(platformConfig, example);
            }
        } catch (Exception e) {
            DMSException.writeFail(logger, e);
        }
        return result;
    }

    public PlatformConfig getInfoByKey(String platformKey) {
        PlatformConfig platformConfig = new PlatformConfig();
        try {
            if (platformKey.contains("aliOss") || platformKey.contains("weixin")) {
                platformConfig = null;
            } else {
                PlatformConfigExample example = new PlatformConfigExample();
                example.createCriteria().andPlatformKeyEqualTo(platformKey);
                List<PlatformConfig> list = platformConfigMapper.selectByExample(example);
                if (list != null && list.size() > 0) {
                    platformConfig = list.get(0);
                }
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return platformConfig;
    }

    /**
     * 根据key查询平台信息-内部专用方法
     *
     * @param platformKey
     * @return
     */
    public PlatformConfig getPlatformConfigByKey(String platformKey) {
        PlatformConfig platformConfig = new PlatformConfig();
        try {
            PlatformConfigExample example = new PlatformConfigExample();
            example.createCriteria().andPlatformKeyEqualTo(platformKey);
            List<PlatformConfig> list = platformConfigMapper.selectByExample(example);
            if (list != null && list.size() > 0) {
                platformConfig = list.get(0);
            }
        } catch (Exception e) {
            DMSException.readFail(logger, e);
        }
        return platformConfig;
    }

    public PlatformInfoVO getPlatformInfo() {
        PlatformInfoVO platformConfig = new PlatformInfoVO();
        List<String> keys = Lists.newArrayList("platform_demo", "platform_banner", "platform_beian", "platform_owner", "platform_url", "platform_name");
        PlatformConfigExample example = new PlatformConfigExample();
        example.createCriteria().andPlatformKeyIn(keys);
        List<PlatformConfig> list = platformConfigMapper.selectByExample(example);
        if (Tools.isEmpty(list)) {
            return platformConfig;
        }

        for (PlatformConfig config : list) {
            if ("platform_demo".equals(config.getPlatformKey())) {
                platformConfig.setDemoInfo(config.getPlatformValue());
            } else if ("platform_banner".equals(config.getPlatformKey())) {
                platformConfig.setBanner(config.getPlatformValue());
            } else if ("platform_beian".equals(config.getPlatformKey())) {
                platformConfig.setBeian(config.getPlatformValue());
            } else if ("platform_owner".equals(config.getPlatformKey())) {
                platformConfig.setOwner(config.getPlatformValue());
            } else if ("platform_url".equals(config.getPlatformKey())) {
                platformConfig.setUrl(config.getPlatformValue());
            } else if ("platform_name".equals(config.getPlatformKey())) {
                platformConfig.setName(config.getPlatformValue());
            }
        }
        return platformConfig;
    }
}
