package com.wisenable.dms.controller;

import com.wisenable.dms.datasource.vo.ShipperSimpleVO;
import com.wisenable.dms.service.shipper.ShipperService;
import com.wisenable.dms.utils.BaseResponseList;
import com.wisenable.dms.utils.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/shipper")
@Api(tags = {"客户-零售商"})
public class ShipperController {

    @Resource
    private ShipperService shipperService;

    @GetMapping("/getList")
    @ApiOperation(value = "获取客户列表")
    public BaseResponseList<ShipperSimpleVO> getShipperList(HttpServletRequest request) {

        String token = request.getHeader("X-Access-Token");
        Long tenantId = Tools.getTenantIdByToken(token);
        List<ShipperSimpleVO> voList = shipperService.getShipperList(tenantId);
        return BaseResponseList.ok(voList);
    }
}
