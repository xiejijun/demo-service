package com.wisenable.dms.service.purchase;

import com.wisenable.dms.api.vo.PurchaseReq;
import com.wisenable.dms.api.vo.PurchaseUpdateStatusReq;
import com.wisenable.dms.datasource.entities.PurchaseOrder;
import com.wisenable.dms.datasource.entities.Shipper;
import com.wisenable.dms.datasource.request.PurchaseListReq;
import com.wisenable.dms.datasource.request.PurchaseUpdateReq;
import com.wisenable.dms.datasource.vo.PurchaseDetailVo;
import com.wisenable.dms.datasource.vo.PurchaseListVo;
import com.wisenable.dms.exception.BusinessException;
import com.wisenable.dms.utils.PageQueryInfo;

import javax.servlet.http.HttpServletRequest;

public interface PurchaseService {
    PageQueryInfo<PurchaseListVo> getPurchase(PurchaseListReq req);

    PurchaseDetailVo getPurchaseById(Long id);

    PurchaseOrder save(PurchaseReq purchaseReq, Shipper shipper) throws BusinessException;

    Integer updateStatus(PurchaseUpdateReq purchaseUpdateReq, HttpServletRequest request) throws BusinessException;

    Integer updateStatus(PurchaseUpdateStatusReq req, Shipper shipper) throws BusinessException;
    Integer updateRemark(PurchaseUpdateStatusReq req, Shipper shipper) throws BusinessException;

    Integer rollbackStatus(PurchaseUpdateReq purchaseUpdateReq, HttpServletRequest request) throws BusinessException;
    Integer rollbackStatus(PurchaseUpdateStatusReq req, Shipper shipper) throws BusinessException;

}
