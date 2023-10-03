package com.boluo.hr.exception;

import org.boluo.hr.pojo.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/18 - 23:24
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

    @ExceptionHandler(Exception.class)
    public RespBean otherExceptionHandle(Exception e) {
        logger.error("其他异常: " + e.getMessage());
        return RespBean.error("其他异常: " + e.getMessage());
    }

}
