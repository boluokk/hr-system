package org.boluo.hr.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 🍍
 * @date 2023/10/25
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 内容
     */
    String value() default "";
}
