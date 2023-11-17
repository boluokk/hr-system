package org.bluo.common.redis.util;

/**
 * @author boluo
 * @date 2023/11/04
 */
public class RedisConstants {
    public static final Long CACHE_NULL_TTL = 120L;

    public static final String CACHE_LOCK_KEY = "lock:";

    public static final Long CACHE_LOCK_TTL = 10L;
}
