package com.wisenable.dms.service.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.wisenable.dms.constants.BusinessConstants;
import com.wisenable.dms.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisService {

    @Resource
    public RedisTemplate redisTemplate;

    public static final String ACCESS_TOKEN = "X-Access-Token";

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     *  从session中获取信息
     */
    public Object getObjectFromSessionByKey(HttpServletRequest request, String key){
        Object obj=null;
        if(request==null){
            return null;
        }
        String token = request.getHeader(ACCESS_TOKEN);
        if(token!=null) {
            //开启redis，用户数据放在redis中，从redis中获取
            if(redisTemplate.opsForHash().hasKey(token,key)){
                //redis中存在，拿出来使用
                obj=redisTemplate.opsForHash().get(token,key);
                redisTemplate.expire(token, BusinessConstants.MAX_SESSION_IN_SECONDS, TimeUnit.SECONDS);
            }
        }
        return obj;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     *  将信息放入session或者redis中
     */
    public void storageObjectBySession(String token, String key, Object obj) {
        //开启redis，用户数据放到redis中
        redisTemplate.opsForHash().put(token, key, obj.toString());
        redisTemplate.expire(token, BusinessConstants.MAX_SESSION_IN_SECONDS, TimeUnit.SECONDS);
    }

    /**
     *  description:
     *  将信息放入session或者redis中
     */
    public void storageCaptchaObject(String verifyKey, String codeNum) {
        //把验证码放到redis中
        redisTemplate.opsForValue().set(verifyKey, codeNum, BusinessConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    public void deleteObjectBySession(HttpServletRequest request, String key){
        if(request!=null){
            String token = request.getHeader(ACCESS_TOKEN);
            if(StringUtil.isNotEmpty(token)){
                //开启redis，用户数据放在redis中，从redis中删除
                redisTemplate.opsForHash().delete(token, key);
            }
        }
    }

    /**
     * 将信息从redis中移除，比对user和ip
     */
    public void deleteObjectByUserAndIp(Long userId, String clientIp){
        Set<String> tokens = redisTemplate.keys("*");
        for(String token : tokens) {
            Object userIdValue = redisTemplate.opsForHash().get(token, "userId");
            Object clientIpValue = redisTemplate.opsForHash().get(token, "clientIp");
            if(userIdValue!=null && clientIpValue!=null && userIdValue.equals(userId.toString()) && clientIpValue.equals(clientIp)) {
                redisTemplate.opsForHash().delete(token, "userId");
            }
        }
    }

    public void cacheValue(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj, 1, TimeUnit.HOURS);
    }

    public void cacheValue(String key, Object obj, long second) {
        redisTemplate.opsForValue().set(key, obj, second, TimeUnit.SECONDS);
    }

    public Object getValue(String key) {
       return redisTemplate.opsForValue().get(key);
    }
}
