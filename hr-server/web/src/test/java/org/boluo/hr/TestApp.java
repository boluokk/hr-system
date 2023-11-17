package org.boluo.hr;

import org.bluo.common.redis.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 🍍
 * @date 2023/10/21
 */

@SpringBootTest
public class TestApp {

    @Resource
    RedisCache redisCache;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        redisCache.set("test", "test", 10L, TimeUnit.SECONDS);
    }
}
