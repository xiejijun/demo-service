package com.wisenable.dms.service.purchase;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.wisenable.dms.api.vo.PurchaseDetailDTO;
import com.wisenable.dms.api.vo.PurchaseOrderMsgVO;
import com.wisenable.dms.api.vo.PurchaseReq;
import com.wisenable.dms.api.vo.PurchaseUpdateStatusReq;
import com.wisenable.dms.constants.*;
import com.wisenable.dms.datasource.entities.*;
import com.wisenable.dms.datasource.mappers.*;
import com.wisenable.dms.datasource.request.PurchaseListReq;
import com.wisenable.dms.datasource.request.PurchaseUpdateReq;
import com.wisenable.dms.datasource.vo.*;
import com.wisenable.dms.exception.BusinessException;
import com.wisenable.dms.service.log.LogService;
import com.wisenable.dms.service.platformConfig.PlatformConfigService;
import com.wisenable.dms.service.shipper.ShipperService;
import com.wisenable.dms.utils.ErpInfo;
import com.wisenable.dms.utils.HttpClient;
import com.wisenable.dms.utils.PageQueryInfo;
import com.wisenable.dms.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private static final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    @Resource
    private PurchaseOrderMapperEx purchaseOrderMapperEx;

    @Resource
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;

    @Resource
    private ShipperMapper shipperMapper;

    @Resource
    private PrescriptionMapper prescriptionMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LogService logService;

    @Resource
    private ShipperService shipperService;

    @Resource
    private PlatformConfigService platformConfigService;

    @Override
    public PageQueryInfo<PurchaseListVo> getPurchase(PurchaseListReq req) {
        int count = purchaseOrderMapper.countPurchaseList(req);
        if (count <= 0) {
            return PageQueryInfo.ok(req.getPageNo(), req.getPageSize(), 0L, Lists.newArrayList());
        }

        List<PurchaseDTO> list = purchaseOrderMapper.getPurchaseList(req.getOffset(), req.getRows(), req);
        List<Map<String, Object>> totalInfo = purchaseOrderMapper.sumPurchase(req);

        PageQueryInfo<PurchaseListVo> pageQueryInfo = PageQueryInfo.ok(req.getPageNo(), req.getPageSize(), (long) count, trans(list));

        if (Tools.isNotEmpty(totalInfo)) {
            pageQueryInfo.setObj(totalInfo.get(0));
        }

        return pageQueryInfo;
    }

    @Override
    public PurchaseDetailVo getPurchaseById(Long id) {

        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(id);
        if (purchaseOrder == null) {
            return null;
        }
        PurchaseDetailVo vo = trans(purchaseOrder);

        PurchaseOrderDetailExample example = new PurchaseOrderDetailExample();
        example.createCriteria().andOrderIdEqualTo(id);
        List<PurchaseOrderDetail> orderDetails = purchaseOrderDetailMapper.selectByExample(example);
        if (Tools.isNotEmpty(orderDetails)) {
            appendOrderDetail(vo, orderDetails);
        }


        PrescriptionExample prescriptionExample = new PrescriptionExample();
        prescriptionExample.createCriteria().andTypeEqualTo(PrescriptionEm.PURCHASE_ORDER.getType()).andOrderIdEqualTo(id);
        List<Prescription> prescriptions = prescriptionMapper.selectByExample(prescriptionExample);
        if (Tools.isNotEmpty(prescriptions)) {
            appendPrescription(vo, prescriptions.get(0));
        } else {
            vo.setPrescriptions(Lists.newArrayList());
        }
        return vo;
    }

    private void appendOrderDetail(PurchaseDetailVo vo, List<PurchaseOrderDetail> orderDetails) {
        List<PurchaseOrderDetailDTO> list = new ArrayList<>();
        vo.setDetails(list);
        if (Tools.isEmpty(orderDetails)) {
            return;
        }

        for (PurchaseOrderDetail orderDetail : orderDetails) {
            PurchaseOrderDetailDTO dto = BeanUtil.copyProperties(orderDetail, PurchaseOrderDetailDTO.class);
            dto.setPrice(orderDetail.getSalePrice());
            list.add(dto);

            if (CategoryEm.LENS.name().equalsIgnoreCase(orderDetail.getCategory()) || CategoryEm.CONTACT_LENSES.name().equalsIgnoreCase(orderDetail.getCategory())) {
                dto.setDegree(Tools.getDefaultStr(orderDetail.getSph(), "") + "/" + Tools.getDefaultStr(orderDetail.getCyl(), ""));
            } else {
                dto.setDegree(dto.getDegree());
            }
            dto.setCategory(CategoryEm.getCategoryCnName(orderDetail.getCategory()));
        }
    }

    private void appendPrescription(PurchaseDetailVo vo, Prescription prescription) {
        List<PrescriptionVO> prescriptionVOS = new ArrayList<>();
        PrescriptionVO od = new PrescriptionVO();
        PrescriptionVO os = new PrescriptionVO();
        prescriptionVOS.add(od);
        prescriptionVOS.add(os);
        vo.setPrescriptions(prescriptionVOS);

        od.setType(GlassesConstants.OD);
        od.setSph(prescription.getOdSph());
        od.setCyl(prescription.getOdCyl());
        od.setAxis(prescription.getOdAxis());
        od.setVa(prescription.getOdVa());
        od.setPd(prescription.getOdPd());
        od.setAdd(prescription.getOdAdd());
        od.setPh(prescription.getOdPh());
        od.setBc(prescription.getOdBc());

        os.setType(GlassesConstants.OS);
        os.setSph(prescription.getOsSph());
        os.setCyl(prescription.getOsCyl());
        os.setAxis(prescription.getOsAxis());
        os.setVa(prescription.getOsVa());
        os.setPd(prescription.getOsPd());
        os.setAdd(prescription.getOsAdd());
        os.setPh(prescription.getOsPh());
        os.setBc(prescription.getOsBc());
    }

    private List<PurchaseListVo> trans(List<PurchaseDTO> list) {
        List<PurchaseListVo> voList = Lists.newArrayList();
        if (list == null || list.size() == 0) {
            return voList;
        }


        Set<Long> shipperIds = list.stream().map(PurchaseDTO::getShipperId).collect(Collectors.toSet());
        ShipperExample example = new ShipperExample();
        example.createCriteria().andIdIn(new ArrayList<>(shipperIds));
        List<Shipper> shippers = shipperMapper.selectByExample(example);
        Map<Long, String> shipperMap = shippers.stream().collect(Collectors.toMap(Shipper::getId, Shipper::getShipperName));

        List<Long> auditList = list.stream().map(PurchaseDTO::getAuditId).collect(Collectors.toList());
        Map<Long, String> userNames = getUserName(auditList);
        Long currentId = null;
        for (PurchaseDTO purchaseDTO : list) {
            PurchaseListVo vo = new PurchaseListVo();
            BeanUtil.copyProperties(purchaseDTO, vo);
            vo.setOrderStatusCn(OrderStatusEm.mapCnName(vo.getOrderStatus()));
            vo.setWorkTypeCn(WorkTypeEm.getWorkTypeStr(vo.getWorkType()));
            if (purchaseDTO.getOrderTime() != null) {
                vo.setOrderTime(DateUtil.format(purchaseDTO.getOrderTime(), DatePattern.NORM_DATETIME_FORMAT));
            }
            vo.setAuditUser(userNames.get(purchaseDTO.getAuditId()));


            if (CategoryEm.LENS.name().equalsIgnoreCase(purchaseDTO.getCategory()) || CategoryEm.CONTACT_LENSES.name().equalsIgnoreCase(purchaseDTO.getCategory())) {
                vo.setDegree(Tools.getDefaultStr(purchaseDTO.getSph(), "") + "/" + Tools.getDefaultStr(purchaseDTO.getCyl(), ""));
            } else {
                vo.setDegree(purchaseDTO.getDegree());
            }
            vo.setCategory(CategoryEm.getCategoryCnName(vo.getCategory()));
            vo.setShipperName(shipperMap.get(purchaseDTO.getShipperId()));
            if (currentId == null || !Objects.equals(currentId, purchaseDTO.getId())) {
                currentId = purchaseDTO.getId();
            } else {
                vo.setWorkTypeCn("");
                vo.setOrderTime("");
                vo.setOrderStatusCn("");
                vo.setOrderStatus(null);
                vo.setShipperName("");
                vo.setAuditId(null);
                vo.setAuditUser("");
                vo.setOrderNo("");
                vo.setRemark("");
                vo.setShipperName("");
                vo.setShipperId(null);
            }
            voList.add(vo);
        }

        return voList;
    }

    private PurchaseDetailVo trans(PurchaseOrder dto) {
        if (dto == null) {
            return null;
        }

        PurchaseDetailVo vo = new PurchaseDetailVo();
        BeanUtil.copyProperties(dto, vo);
        vo.setOrderStatusCn(OrderStatusEm.mapCnName(vo.getOrderStatus()));
        vo.setWorkTypeCn(WorkTypeEm.getWorkTypeStr(vo.getWorkType()));
        if (dto.getOrderTime() != null) {
            vo.setOrderTime(DateUtil.format(dto.getOrderTime(), DatePattern.NORM_DATETIME_FORMAT));
        }
        Shipper shipper = shipperMapper.selectByPrimaryKey(vo.getShipperId());
        if (shipper != null) {
            vo.setShipperName(shipper.getShipperName());
        }
        return vo;
    }

    @Override
    @Transactional
    public PurchaseOrder save(PurchaseReq purchaseReq, Shipper shipper) throws BusinessException {
        String salesOrderNo = purchaseReq.getSalesOrderNo();
        if (Tools.isEmpty(salesOrderNo)) {
            throw new BusinessException(ErpInfo.BAD_REQUEST.code, "salesOrderNo is invalid");
        }

        Date orderTime;
        try {
            orderTime = DateUtil.parse(purchaseReq.getSalesOrderTime(), DatePattern.NORM_DATETIME_FORMAT);
        } catch (Exception e) {
            throw new BusinessException(ErpInfo.BAD_REQUEST.code, "saleOrderTime is invalid, should be [yyyy-MM-dd HH:mm:ss]");
        }

        String orderNo = purchaseReq.getOrderNo();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNo(orderNo);
        purchaseOrder.setSalesOrderNo(salesOrderNo);
        purchaseOrder.setTotalAmount(purchaseReq.getTotalAmount());
        purchaseOrder.setTotalSalePrice(purchaseReq.getTotalSalePrice());
//        purchaseOrder.setDiscount(purchaseReq.getProductCount());
        purchaseOrder.setOrderStatus(OrderStatusEm.PENDING.getStatus());
        purchaseOrder.setShipperId(shipper.getId());
        purchaseOrder.setTenantId(shipper.getTenantId());
        purchaseOrder.setSalesOrderTime(orderTime);
        purchaseOrder.setWorkType(purchaseReq.getWorkType());
        purchaseOrder.setRemark(purchaseReq.getRemark());
        purchaseOrder.setOrderTime(new Date());
        int row = purchaseOrderMapper.insertSelective(purchaseOrder);
        log.info("insert result {}, id={}", row, purchaseOrder.getId());


        if (purchaseReq.getPrescription() != null) {
            boolean existsPrescription = false;
            if (purchaseReq.getPrescription().getId() != null && purchaseReq.getPrescription().getId() > 0) {
                PrescriptionExample example = new PrescriptionExample();
                example.createCriteria().andOrderIdEqualTo(purchaseOrder.getId()).andTypeEqualTo(PrescriptionEm.PURCHASE_ORDER.getType());
                List<Prescription> prescriptions = prescriptionMapper.selectByExample(example);
                if (prescriptions != null && prescriptions.size() > 0) {
                    existsPrescription = true;
                }
            }

            if (!existsPrescription) {
                Prescription prescription = BeanUtil.copyProperties(purchaseReq.getPrescription(), Prescription.class);
                prescription.setOrderId(purchaseOrder.getId());
                prescription.setId(null);
                prescription.setType(PrescriptionEm.PURCHASE_ORDER.getType());
                prescription.setShipperId(shipper.getId());
                prescription.setTenantId(shipper.getTenantId());
                prescriptionMapper.insertSelective(prescription);
            }
        }

        if (Tools.isNotEmpty(purchaseReq.getDetails())) {
            for (PurchaseDetailDTO detail : purchaseReq.getDetails()) {
                PurchaseOrderDetail orderDetail = BeanUtil.copyProperties(detail, PurchaseOrderDetail.class);
                orderDetail.setOrderId(purchaseOrder.getId());
                orderDetail.setTenantId(shipper.getTenantId());
                orderDetail.setSalePrice(detail.getPrice());
                purchaseOrderDetailMapper.insertSelective(orderDetail);
            }
        }

        // 发送通知
        sendMsg(purchaseOrder);
        return purchaseOrder;
    }

    private void sendMsg(PurchaseOrder order) {
        Long tenantId = order.getTenantId();
        UserExample example = new UserExample();
        example.createCriteria().andTenantIdEqualTo(tenantId).andWistoreMsgOpenIdIsNotNull().andWistoreMsgOpenIdNotEqualTo("");
        List<User> list = userMapper.selectByExample(example);
        if (Tools.isEmpty(list)) {
            log.warn("未绑定任何openId");
            return;
        }

        PlatformConfig platformConfig = platformConfigService.getInfoByKey("env");
        String host = platformConfig.getPlatformValue();

        String url = "http://localhost:8099/api/wechat/notify/sendPurchaseMsgToEmp";
        PurchaseOrderMsgVO vo = new PurchaseOrderMsgVO();
        Shipper shipper = shipperService.getShipperById(order.getShipperId());
        if (shipper != null) {
            vo.setCustomerName(shipper.getShipperName());
        }
        vo.setOrderTime(DateUtil.formatDateTime(order.getOrderTime()));
        vo.setOrderType("订货单");
        vo.setOrderNo(order.getOrderNo());

        String linkUrl;
        if ("dev".equals(host)) {
            linkUrl = "https://dev-dms.wisenable.com/instock/list";
        } else {
            linkUrl = "https://dms.wisenable.com/instock/list";
        }

        vo.setLinkUrl(linkUrl);
        List<String> openIds = list.stream().map(User::getWistoreMsgOpenId).collect(Collectors.toList());
        vo.setOpenIds(openIds);

        try {
            String body = JSONUtil.toJsonStr(vo);
            String response = HttpClient.httpPost(url, body);
            log.info("request url:{}, body:{}, response: {}",url, body, response);}
        catch (Exception e) {
            log.error("failed to sendMsg.", e);
        }
    }

    @Override
    public Integer updateStatus(PurchaseUpdateReq purchaseUpdateReq, HttpServletRequest request) throws BusinessException {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseUpdateReq.getId());
        if (purchaseOrder == null) {
            throw new BusinessException(500, "订单不存在");
        }

        boolean status = checkStatusForUpdate(purchaseOrder.getOrderStatus(), purchaseUpdateReq.getStatus());
        if (!status) {
            throw new BusinessException(500, "当前订单状态不允许被更新");
        }

        PurchaseOrder updateOrder = new PurchaseOrder();
        updateOrder.setId(purchaseUpdateReq.getId());
        updateOrder.setOrderStatus(purchaseUpdateReq.getStatus());
        if (Objects.equals(purchaseUpdateReq.getStatus(), OrderStatusEm.DESPATCHED.getStatus())) {
            updateOrder.setTrackingNo(purchaseUpdateReq.getTrackingNo());
            updateOrder.setThirdNo(purchaseUpdateReq.getThirdNo());
        }

        int row = purchaseOrderMapper.updateByPrimaryKeySelective(updateOrder);
        if (row > 0) {
            String content = OrderStatusEm.getAction(OrderStatusEm.status(purchaseUpdateReq.getStatus()));
            content += purchaseOrder.getOrderNo();
            logService.insertLog(BusinessConstants.SUB_TYPE_PURCHASE_IN, content, request);
            PurchaseUpdateStatusReq req = new PurchaseUpdateStatusReq();
            req.setStatus(purchaseUpdateReq.getStatus());
            req.setOrderNo(purchaseOrder.getOrderNo());
            req.setThirdNo(purchaseUpdateReq.getThirdNo());
            req.setTrackingNo(purchaseUpdateReq.getTrackingNo());
            notify(purchaseOrder.getShipperId(), req);
        }
        return row;
    }

    @Override
    public Integer updateStatus(PurchaseUpdateStatusReq req, Shipper shipper) throws BusinessException {
        PurchaseOrder purchaseOrder = purchaseOrderMapperEx.selectByOrderNo(req.getOrderNo(), shipper.getId());
        if (purchaseOrder == null) {
            throw new BusinessException(500, "订单不存在");
        }

        boolean status = checkStatusForUpdate(purchaseOrder.getOrderStatus(), req.getStatus());
        if (!status) {
            throw new BusinessException(500, "当前订单状态不允许被更新");
        }

        PurchaseOrder updateOrder = new PurchaseOrder();
        updateOrder.setId(purchaseOrder.getId());
        updateOrder.setOrderStatus(req.getStatus());
        return purchaseOrderMapper.updateByPrimaryKeySelective(updateOrder);
    }

    @Override
    public Integer updateRemark(PurchaseUpdateStatusReq req, Shipper shipper) throws BusinessException {
        PurchaseOrder purchaseOrder = purchaseOrderMapperEx.selectByOrderNo(req.getOrderNo(), shipper.getId());
        if (purchaseOrder == null) {
            throw new BusinessException(500, "订单不存在");
        }


        PurchaseOrder updateOrder = new PurchaseOrder();
        updateOrder.setId(purchaseOrder.getId());
        if (Tools.isEmpty(req.getRemark())) {
            updateOrder.setRemark("");
        } else {
            updateOrder.setRemark(req.getRemark());
        }

        return purchaseOrderMapper.updateByPrimaryKeySelective(updateOrder);
    }

    @Override
    public Integer rollbackStatus(PurchaseUpdateReq purchaseUpdateReq, HttpServletRequest request) throws BusinessException {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseUpdateReq.getId());
        if (purchaseOrder == null) {
            throw new BusinessException(500, "订单不存在");
        }

        boolean status = checkStatusForRollback(purchaseOrder.getOrderStatus(), purchaseUpdateReq.getStatus());
        if (!status) {
            throw new BusinessException(500, "当前订单状态不允许被更新");
        }

        PurchaseOrder updateOrder = new PurchaseOrder();
        updateOrder.setId(purchaseUpdateReq.getId());
        updateOrder.setOrderStatus(purchaseUpdateReq.getStatus());
        if (Objects.equals(purchaseUpdateReq.getStatus(), OrderStatusEm.RECEIVED.getStatus())) {
            updateOrder.setTrackingNo("");
            updateOrder.setThirdNo("");
        }

        int row = purchaseOrderMapper.updateByPrimaryKeySelective(updateOrder);
        if (row > 0) {
            String content = OrderStatusEm.getRollAction(OrderStatusEm.status(purchaseUpdateReq.getStatus()));
            content += purchaseOrder.getOrderNo();
            logService.insertLog(BusinessConstants.SUB_TYPE_PURCHASE_IN, content, request);

            PurchaseUpdateStatusReq req = new PurchaseUpdateStatusReq();
            req.setStatus(purchaseUpdateReq.getStatus());
            req.setOrderNo(purchaseOrder.getOrderNo());
            req.setThirdNo(purchaseUpdateReq.getThirdNo());
            req.setTrackingNo(purchaseUpdateReq.getTrackingNo());
            notify(purchaseOrder.getShipperId(), req);
        }
        return row;
    }

    @Override
    public Integer rollbackStatus(PurchaseUpdateStatusReq req, Shipper shipper) throws BusinessException {
        PurchaseOrder purchaseOrder = purchaseOrderMapperEx.selectByOrderNo(req.getOrderNo(), shipper.getId());
        if (purchaseOrder == null) {
            throw new BusinessException(500, "订单不存在");
        }

        boolean status = checkStatusForRollback(purchaseOrder.getOrderStatus(), req.getStatus());
        if (!status) {
            throw new BusinessException(500, "当前订单状态不允许被更新");
        }

        PurchaseOrder updateOrder = new PurchaseOrder();
        updateOrder.setId(purchaseOrder.getId());
        updateOrder.setOrderStatus(req.getStatus());

        if (Objects.equals(req.getStatus(), OrderStatusEm.CANCELED.getStatus())) {
            PurchaseOrderDetailExample example = new PurchaseOrderDetailExample();
            example.createCriteria().andOrderIdEqualTo(purchaseOrder.getId());
            purchaseOrderDetailMapper.deleteByExample(example);

            PrescriptionExample prescriptionExample = new PrescriptionExample();
            prescriptionExample.createCriteria().andOrderIdEqualTo(purchaseOrder.getId()).andTypeEqualTo(PrescriptionEm.PURCHASE_ORDER.getType());
            prescriptionMapper.deleteByExample(prescriptionExample);

            return purchaseOrderMapper.deleteByPrimaryKey(purchaseOrder.getId());
        }

        return purchaseOrderMapper.updateByPrimaryKeySelective(updateOrder);
    }

    private boolean checkStatusForUpdate(Integer current, Integer next) throws BusinessException {
        if (current == null || next == null) {
            throw new BusinessException(500, "当前订单状态不允许被更新");
        }

        if (Objects.equals(OrderStatusEm.DELIVERED.getStatus(), next) || Objects.equals(OrderStatusEm.DESPATCHED.getStatus(), next)
                || Objects.equals(OrderStatusEm.RECEIVED.getStatus(), next)
        ) {
            return current + 1 == next;
        } else {
            return false;
        }
    }

    private boolean checkStatusForRollback(Integer current, Integer next) throws BusinessException {
        if (current == null || next == null) {
            throw new BusinessException(500, "当前订单状态不允许被更新");
        }

        if (Objects.equals(OrderStatusEm.DELIVERED.getStatus(), current) || Objects.equals(OrderStatusEm.DESPATCHED.getStatus(), current)
                || Objects.equals(OrderStatusEm.RECEIVED.getStatus(), current) || Objects.equals(OrderStatusEm.PENDING.getStatus(), current)
        ) {
            return current - 1 == next;
        } else {
            return false;
        }
    }

    private Map<Long, String> getUserName(List<Long> userId) {
        Map<Long, String> result = new HashMap<>();
        if (Tools.isEmpty(userId)) {
            return result;
        }

        UserExample example = new UserExample();
        example.createCriteria().andIdIn(userId);
        List<User> users = userMapper.selectByExample(example);
        if (Tools.isNotEmpty(users)) {
            for (User user : users) {
                result.put(user.getId(), user.getUsername());
            }
        }
        return result;
    }

    private void notify(Long shipperId, PurchaseUpdateStatusReq req) {
        Shipper shipper = shipperService.getShipperById(shipperId);
        if (shipper != null && Tools.isNotEmpty(shipper.getNotifyUrl())) {
            try {
                String url = shipper.getNotifyUrl() + "/notify/updateStatus";
                String body = JSONUtil.toJsonStr(req);
                String response = HttpClient.httpPost(url, body);
                log.info("request url:{}, body:{}, response: {}",url, body, response);}
            catch (Exception e) {
                log.error("failed to update status.", e);
            }
        }
    }

}
