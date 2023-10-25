package org.boluo.hr.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.OperatorLog;
import org.boluo.hr.service.OperatorLogService;
import org.boluo.hr.service.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 全局日志
 *
 * @author 🍍
 * @date 2023/10/25
 */
@Component
@Slf4j
@Aspect
public class WebLogAspect {

    private final OperatorLogService operatorLogService;

    @Autowired
    public WebLogAspect(OperatorLogService operatorLogService) {
        this.operatorLogService = operatorLogService;
    }

    /**
     * 记录日志
     */
    public void recordLog(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log logContent = method.getAnnotation(Log.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setHrId(HrUtils.getCurrentHr().getId());
        operatorLog.setAddress(request.getRemoteAddr());
        operatorLog.setContent(logContent.value());
        operatorLog.setEvent(request.getRequestURI());
        operatorLog.setCreateDate(new Date());
        operatorLog.setType(request.getMethod());
        if (!operatorLogService.insertOperatorLog(operatorLog)) {
            log.warn("未新增记录成功: {}", operatorLog);
        }
    }

    @Before("execution(public * org.boluo.hr.config.login_handle..*(..))")
    public void beforeLogout(JoinPoint joinPoint) {
        recordLog(joinPoint);
    }

    @Before("execution(public * org.boluo.hr.controller..*(..))")
    public void doBefore(JoinPoint joinPoint) {
        recordLog(joinPoint);
    }
}
