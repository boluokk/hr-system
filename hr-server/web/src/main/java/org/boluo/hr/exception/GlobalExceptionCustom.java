package org.boluo.hr.exception;

import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionCustom {
    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RespBean businessExceptionHandle(BusinessException e) {
        log.warn("操作异常: " + e.getMessage());
        return RespBean.error(e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public RespBean otherExceptionHandle(Exception e) {
        log.error("其他异常: {}", e.getClass());
        return RespBean.error("服务器异常..");
    }

}
