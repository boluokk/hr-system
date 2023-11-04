package org.boluo.hr.annotation;

import org.boluo.hr.enums.uploadFileEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 上传文件检测注解
 *
 * @author 🍍
 * @date 2023/11/03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface uploadFileCheck {
    /**
     * 错误提示
     */
    String message() default "不支持当前类型";

    /**
     * 支持类型
     */
    uploadFileEnum[] supportType() default {};

    /**
     * 支持类型字符串
     */
    String[] supportTypeStr() default {};

    /**
     * 校验类型
     */
    checkType checkType() default checkType.SUFFIX;

    enum checkType {
        SUFFIX, MAGIC_NUMBER, SUFFIX_MAGIC_NUMBER;
    }
}
