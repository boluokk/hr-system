package org.boluo.hr.exception;

import lombok.extern.slf4j.Slf4j;
import org.bluo.global.pojo.RespBean;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionCustom {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RespBean httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return new RespBean(HttpStatus.BAD_REQUEST.value(), "请求体异常");
    }

    @ExceptionHandler(RequestRejectedException.class)
    public RespBean requestRejectedExceptionHandler(RequestRejectedException e) {
        return new RespBean(HttpStatus.BAD_REQUEST.value(), "请求路径异常");
    }

    // 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public RespBean bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new RespBean(HttpStatus.BAD_REQUEST.value(), collect);
    }

    // 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespBean methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new RespBean(HttpStatus.BAD_REQUEST.value(), collect);
    }

    // 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public RespBean constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return new RespBean(HttpStatus.BAD_REQUEST.value(), collect);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RespBean methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        return new RespBean(HttpStatus.BAD_REQUEST.value(), "参数类型不匹配");
    }

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
        log.error("其他异常: {}", e);
        return RespBean.error("服务器异常..");
    }

}
