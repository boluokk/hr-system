package org.bluo.common.redis.config;

import org.bluo.common.redis.util.RedisCache;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author boluo
 * @date 2023/11/17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisTemplateConfig.class, RedisCache.class})
@Target(ElementType.TYPE)
public @interface EnableRedisConfig {
}
