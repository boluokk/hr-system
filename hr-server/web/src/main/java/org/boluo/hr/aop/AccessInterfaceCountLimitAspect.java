package org.boluo.hr.aop;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.bluo.common.redis.util.RedisCache;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.exception.BusinessException;
import org.boluo.hr.pojo.Hr;
import org.boluo.hr.util.CheckUtil;
import org.boluo.hr.util.HrUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author boluo
 * @date 2023/11/05
 */
@Component
@Aspect
@Slf4j
public class AccessInterfaceCountLimitAspect {
    @Resource
    private RedisCache redisCache;

    @Value("${custom.config.api_access_interval}")
    private long intervalTime;

    private final static String REG = "[a-z,/A-Z]*";

    @Before("@annotation(log)")
    public void denyAccess(Log log) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (CheckUtil.isNotNull(attributes)) {
            String uri = attributes.getRequest().getRequestURI();
            Matcher matcher = Pattern.compile(REG).matcher(uri);
            if (matcher.find()) {
                uri = matcher.group(0);
            }
            Hr currentHr = HrUtils.getCurrentHr();
            int id = ObjectUtil.isNull(currentHr) ? -1 : currentHr.getId();
            String key = "access_limit:" + id + ":" + uri;
            if (CheckUtil.isNotNull(redisCache.get(key))) {
                redisCache.set(key, 1, intervalTime, TimeUnit.MILLISECONDS);
                throw new BusinessException("访问接口过于频繁");
            }
            redisCache.set(key, 1, intervalTime, TimeUnit.MILLISECONDS);
        }
    }
}
