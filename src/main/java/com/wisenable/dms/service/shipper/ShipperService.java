package com.wisenable.dms.service.shipper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.google.common.collect.Lists;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.datasource.entities.Shipper;
import com.wisenable.dms.datasource.entities.ShipperExample;
import com.wisenable.dms.datasource.mappers.ShipperMapper;
import com.wisenable.dms.datasource.vo.ShipperSimpleVO;
import com.wisenable.dms.exception.BusinessException;
import com.wisenable.dms.service.redis.RedisService;
import com.wisenable.dms.utils.ErpInfo;
import com.wisenable.dms.utils.Tools;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ShipperService {
    private static final Logger logger = LoggerFactory.getLogger(ShipperService.class);
    @Resource
    private ShipperMapper shipperMapper;

    @Resource
    private RedisService redisService;

    public List<ShipperSimpleVO> getShipperList(Long tenantId) {

        ShipperExample example = new ShipperExample();
        example.createCriteria().andTenantIdEqualTo(tenantId);
        List<Shipper> shippers = shipperMapper.selectByExample(example);

        if (shippers != null && shippers.size() > 0) {
            return BeanUtil.copyToList(shippers, ShipperSimpleVO.class);
        }
        return Lists.newArrayList();
    }

    public Shipper getShipperAndCheckSign(String appId, String signature, Long timestamp) throws BusinessException {
        ShipperExample example = new ShipperExample();
        example.createCriteria().andAppIdEqualTo(appId);
        List<Shipper> shippers = shipperMapper.selectByExample(example);

        if (Tools.isEmpty(shippers)) {
            throw new BusinessException(ErpInfo.UNAUTHORIZED.code, "AppId is invalid");
        }
        long current = System.currentTimeMillis();
        if (Math.abs(current - timestamp) > 600000) {
            throw new BusinessException(ErpInfo.UNAUTHORIZED.code, "Timestamp is invalid");
        }

        String secret = shippers.get(0).getSecretKey();
        String s = DigestUtil.md5Hex(timestamp + secret);
        if (!s.equalsIgnoreCase(signature)) {
            throw new BusinessException(ErpInfo.UNAUTHORIZED.code, "Signature is invalid");
        }

        return shippers.get(0);
    }

    public Shipper getShipperById(Long shipperId) {
        String key = BusinessConstants.SHIPPER_KEY + shipperId;
        Object o = redisService.getValue(key);
        if (o == null) {
            Shipper shipper = shipperMapper.selectByPrimaryKey(shipperId);
            if (shipper != null) {
                redisService.cacheValue(key, shipper);
                return shipper;
            }
        } else {
            return (Shipper) o;
        }
        return null;
    }

    public int addAppId(Long tenantId) {
        ShipperExample example = new ShipperExample();
        example.createCriteria().andTenantIdEqualTo(tenantId);
        long count = shipperMapper.countByExample(example);
        if (count > 0) {
            return (int) count;
        }

        Shipper shipper = new Shipper();
        long current = System.currentTimeMillis();
        String sed = DigestUtil.md5Hex("DMS_" + current);
        String appId = "DMS_" + sed.toUpperCase().substring(0, 28);
        String secret = DigestUtil.md5Hex("DMS_" + current + appId).toUpperCase();

        shipper.setAppId(appId);
        shipper.setSecretKey(secret);
        shipper.setShipperName("地球是圆的");
        shipper.setTenantId(tenantId);
        shipper.setNotifyUrl("http://localhost:8080/api/");
        return shipperMapper.insertSelective(shipper);
    }

}
