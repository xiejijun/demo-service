package com.wisenable.dms.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisenable.dms.datasource.entities.PlatformConfig;
import com.wisenable.dms.datasource.vo.PlatformInfoVO;
import com.wisenable.dms.service.platformConfig.PlatformConfigService;
import com.wisenable.dms.utils.BaseResponseInfo;
import com.wisenable.dms.utils.ErpInfo;
import com.wisenable.dms.utils.ResponseJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/platformConfig")
@Api(tags = {"平台参数"})
public class PlatformConfigController {
    private Logger logger = LoggerFactory.getLogger(PlatformConfigController.class);

    @Resource
    private PlatformConfigService platformConfigService;

    /**
     * 获取是否开启注册
     * @param request
     * @return
     */
    @GetMapping(value = "/getPlatform/registerFlag")
    @ApiOperation(value = "获取是否开启注册")
    public String getPlatformRegisterFlag(HttpServletRequest request)throws Exception {
        String res;
        try {
            String platformKey = "register_flag";
            PlatformConfig platformConfig = platformConfigService.getInfoByKey(platformKey);
            res = platformConfig.getPlatformValue();
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            res = "#";
        }
        return res;
    }

    /**
     * 根据platformKey更新platformValue
     * @param object
     * @param request
     * @return
     */
    @PostMapping(value = "/updatePlatformConfigByKey")
    @ApiOperation(value = "根据platformKey更新platformValue")
    public String updatePlatformConfigByKey(@RequestBody JSONObject object,
                                            HttpServletRequest request)throws Exception {
        Map<String, Object> objectMap = new HashMap<>();
        String platformKey = object.getString("platformKey");
        String platformValue = object.getString("platformValue");
        int res = platformConfigService.updatePlatformConfigByKey(platformKey, platformValue);
        if(res > 0) {
            return ResponseJsonUtil.returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return ResponseJsonUtil.returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    /**
     * 根据platformKey查询信息
     * @param platformKey
     * @param request
     * @return
     */
    @GetMapping(value = "/getInfoByKey")
    @ApiOperation(value = "根据platformKey查询信息")
    public BaseResponseInfo getInfoByKey(@RequestParam("platformKey") String platformKey,
                                         HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            PlatformConfig platformConfig = platformConfigService.getInfoByKey(platformKey);
            res.code = 200;
            res.data = platformConfig;
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }


    @GetMapping(value = "/getPlatformInfo")
    @ApiOperation(value = "获取平台信息")
    public PlatformInfoVO getPlatformInfo(HttpServletRequest request) {
       return platformConfigService.getPlatformInfo();
    }
}
