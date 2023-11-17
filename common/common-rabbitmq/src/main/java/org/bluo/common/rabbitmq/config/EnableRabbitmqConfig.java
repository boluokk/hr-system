package org.bluo.common.rabbitmq.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author boluo
 * @date 2023/11/17
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import({RabbitmqTemplateConfig.class})
public @interface EnableRabbitmqConfig {
}
