package org.bluo.common.redis.util;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author boluo
 * @date 2023/11/17
 */
@Data
public class RedisData {
    LocalDateTime expireTime;
    Object data;
}
