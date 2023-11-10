package org.boluo.hr.aop;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.util.CheckUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 前端传入参数记录
 *
 * @author boluo
 * @date 2023/11/09
 */
@Component
@Aspect
@Slf4j
public class ParamLoggerAspect {

    @Around("@annotation(marker)")
    public Object doLog(ProceedingJoinPoint joinPoint, Log marker) throws Throwable {
        Object ret = null;
        ServletRequestAttributes request = (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes());
        Object[] args = joinPoint.getArgs();
        if (request != null) {
            log.info("传入参数: {}", getArgsToString(args, request.getRequest().getRequestURI()));
        }
        ret = joinPoint.proceed();
        log.info("返回参数: {}", JSONUtil.toJsonStr(ret));
        return ret;
    }


    /**
     * 将参数转换成字符串
     *
     * @param args 参数对象
     * @return 参数字符串
     */
    public String getArgsToString(Object[] args, String uri) {
        if (CheckUtil.isNull(args)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append("参数-").append(i).append(":").append(JSONUtil.toJsonStr(args[i])).append(" ");
        }
        // uri
        if (CheckUtil.isNotNull(uri)) {
            sb.append("参数-").append("uri").append(":").append(uri);
        }
        return sb.toString();
    }
}
