package com.wisenable.dms.controller;

import com.wisenable.dms.datasource.entities.MaterialProperty;
import com.wisenable.dms.service.materialProperty.MaterialPropertyService;
import com.wisenable.dms.utils.BaseResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/materialProperty")
@Api(tags = {"商品扩展字段"})
public class MaterialPropertyController {

    private Logger logger = LoggerFactory.getLogger(MaterialPropertyController.class);

    @Resource
    private MaterialPropertyService materialPropertyService;

    @GetMapping(value = "/getAllList")
    @ApiOperation(value = "查询全部商品扩展字段信息")
    public BaseResponseInfo getAllList(HttpServletRequest request) throws Exception{
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            List<MaterialProperty> list = materialPropertyService.getMaterialProperty();
            res.code = 200;
            res.data = list;
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

}
