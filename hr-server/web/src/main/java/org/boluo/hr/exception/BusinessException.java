package org.boluo.hr.exception;

/**
 * @author 🍍
 * @date 2023/10/1
 */
public class BusinessException extends RuntimeException{

    public BusinessException() {
    }

    public BusinessException(String msg) {
        super(msg);
    }
}
