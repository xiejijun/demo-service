package com.wisenable.dms.api;


import com.wisenable.dms.api.vo.PurchaseReq;
import com.wisenable.dms.api.vo.PurchaseUpdateStatusReq;
import com.wisenable.dms.datasource.entities.PurchaseOrder;
import com.wisenable.dms.datasource.entities.Shipper;
import com.wisenable.dms.datasource.entities.User;
import com.wisenable.dms.datasource.entities.UserEx;
import com.wisenable.dms.exception.BusinessException;
import com.wisenable.dms.service.purchase.PurchaseService;
import com.wisenable.dms.service.shipper.ShipperService;
import com.wisenable.dms.service.user.UserService;
import com.wisenable.dms.utils.BaseResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = {"开放接口"})
@RequestMapping("/api/v1")
public class PurchaseApiController {

    @Resource
    private ShipperService shipperService;

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private UserService userService;

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public BaseResponseObject<String> test(@RequestHeader("AppId") String appId,
                                           @RequestHeader("Signature") String sign,
                                           @RequestHeader("Timestamp") Long timestamp) throws BusinessException {
        shipperService.getShipperAndCheckSign(appId, sign, timestamp);

        return BaseResponseObject.ok("ok");
    }

    @PostMapping("/purchase/create")
    @ApiOperation(value = "创建订货单")
    public BaseResponseObject<PurchaseOrder> addPurchase(@RequestHeader("AppId") String appId,
                                                         @RequestHeader("Signature") String sign,
                                                         @RequestHeader("Timestamp") Long timestamp,
                                                         @RequestBody PurchaseReq purchaseReq) throws BusinessException {
        Shipper shipper = shipperService.getShipperAndCheckSign(appId, sign, timestamp);
        return BaseResponseObject.ok(purchaseService.save(purchaseReq, shipper));
    }

    @PostMapping("/purchase/updateStatus")
    @ApiOperation(value = "更新订货单")
    public BaseResponseObject<Integer> updatePurchaseStatus(@RequestHeader("AppId") String appId,
                                                         @RequestHeader("Signature") String sign,
                                                         @RequestHeader("Timestamp") Long timestamp,
                                                         @RequestBody PurchaseUpdateStatusReq purchaseUpdateStatusReq) throws BusinessException {
        Shipper shipper = shipperService.getShipperAndCheckSign(appId, sign, timestamp);
        return BaseResponseObject.ok(purchaseService.updateStatus(purchaseUpdateStatusReq, shipper));
    }

    @PostMapping("/purchase/rollbackStatus")
    @ApiOperation(value = "反审订货单")
    public BaseResponseObject<Integer> rollbackPurchaseStatus(@RequestHeader("AppId") String appId,
                                                      @RequestHeader("Signature") String sign,
                                                      @RequestHeader("Timestamp") Long timestamp,
                                                      @RequestBody PurchaseUpdateStatusReq purchaseUpdateStatusReq) throws BusinessException {
        Shipper shipper = shipperService.getShipperAndCheckSign(appId, sign, timestamp);
        return BaseResponseObject.ok(purchaseService.rollbackStatus(purchaseUpdateStatusReq, shipper));
    }

    @PostMapping("/purchase/updateRemark")
    @ApiOperation(value = "更新订货单备注")
    public BaseResponseObject<Integer> updateRemark(@RequestHeader("AppId") String appId,
                                                            @RequestHeader("Signature") String sign,
                                                            @RequestHeader("Timestamp") Long timestamp,
                                                            @RequestBody PurchaseUpdateStatusReq purchaseUpdateStatusReq) throws BusinessException {
        Shipper shipper = shipperService.getShipperAndCheckSign(appId, sign, timestamp);
        return BaseResponseObject.ok(purchaseService.updateRemark(purchaseUpdateStatusReq, shipper));
    }

    @GetMapping("/user/binding")
    public BaseResponseObject<Map<String, String>> getUserInfo(@RequestParam("userId") Long userId) {
        Map<String, String> stringMap = new HashMap<>();
        User u = userService.getUser(userId);
        if (u != null) {
            stringMap.put("userId", String.valueOf(u.getId()));
            stringMap.put("openId", u.getWistoreMsgOpenId());
        }
        return BaseResponseObject.ok(stringMap);

    }

    @PostMapping("/user/bindingOpenId")
    public BaseResponseObject<Integer> bindOpenId(@RequestBody Map<String, String> param) {
        if (param.get("userId") == null || param.get("openId") == null) {
            BaseResponseObject.error(400, "userId or openId is empty.");
        }

        UserEx u = new UserEx();
        u.setId(Long.parseLong(param.get("userId")));
        u.setWistoreMsgOpenId(param.get("openId"));
        userService.updateUser(u);
        return BaseResponseObject.ok(1);
    }
}

