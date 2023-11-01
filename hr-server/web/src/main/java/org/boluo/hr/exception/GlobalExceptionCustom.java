package org.boluo.hr.exception;

import org.boluo.hr.pojo.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestControllerAdvice
public class GlobalExceptionCustom {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionCustom.class);

    @ExceptionHandler(SQLException.class)
    public RespBean sqlExceptionHandle(Exception e) {
        logger.error("数据库异常: " + e.getMessage());
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据有关联数据，操作失败!");
        }
        return RespBean.error("数据库异常，操作失败!" + e.getMessage());
    }


    // 业务异常
    @ExceptionHandler(BusinessException.class)
    public RespBean businessExceptionHandle(BusinessException e) {
        logger.error("操作异常: " + e.getMessage());
        return RespBean.error(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public RespBean otherExceptionHandle(Exception e) {
        logger.error("其他异常: {}", e.getClass());
        return RespBean.error("服务器异常..");
    }

}
