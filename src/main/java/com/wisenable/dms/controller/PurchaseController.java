package com.wisenable.dms.controller;

import com.wisenable.dms.constants.OrderStatusEm;
import com.wisenable.dms.datasource.request.PurchaseListReq;
import com.wisenable.dms.datasource.request.PurchaseUpdateReq;
import com.wisenable.dms.datasource.vo.PurchaseDetailVo;
import com.wisenable.dms.datasource.vo.PurchaseListVo;
import com.wisenable.dms.datasource.vo.StatusVO;
import com.wisenable.dms.service.purchase.PurchaseService;
import com.wisenable.dms.utils.BaseResponseList;
import com.wisenable.dms.utils.BaseResponseObject;
import com.wisenable.dms.utils.PageQueryInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchase")
@Api(tags = {"订货单管理"})
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @PostMapping("/getList")
    @ApiOperation(value = "获取订货单列表")
    public PageQueryInfo<PurchaseListVo> getPurchase(@RequestBody PurchaseListReq req) {
        return purchaseService.getPurchase(req);
    }


    @GetMapping("/detail")
    @ApiOperation(value = "获取订货单详情")
    public BaseResponseObject<PurchaseDetailVo> getPurchaseDetail(@RequestParam("id") Long id) {
        return BaseResponseObject.ok(purchaseService.getPurchaseById(id));
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "更新订货单状态")
    public BaseResponseObject<Integer> updateStatus(@RequestBody PurchaseUpdateReq purchaseUpdateReq, HttpServletRequest request) throws Exception {
        return BaseResponseObject.ok(purchaseService.updateStatus(purchaseUpdateReq, request));
    }

    @PostMapping("/rollbackStatus")
    @ApiOperation(value = "回滚订货单状态")
    public BaseResponseObject<Integer> rollbackStatus(@RequestBody PurchaseUpdateReq purchaseUpdateReq, HttpServletRequest request) throws Exception {

        return BaseResponseObject.ok(purchaseService.rollbackStatus(purchaseUpdateReq, request));
    }

    @GetMapping("/status")
    @ApiOperation(value = "获取订货单所有状态")
    public BaseResponseList<StatusVO> getAllStatus() {
        List<StatusVO> voList = new ArrayList<>();
        for (OrderStatusEm value : OrderStatusEm.values()) {
            if (value.getStatus() < 0) {
                continue;
            }
            StatusVO vo = new StatusVO();
            vo.setName(value.getCnName());
            vo.setStatus(value.getStatus());
            voList.add(vo);
        }
        return BaseResponseList.ok(voList);
    }
}
