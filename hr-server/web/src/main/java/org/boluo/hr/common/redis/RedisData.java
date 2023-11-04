package org.boluo.hr.common.redis;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author boluo
 * @date 2023/11/04
 */
@Data
public class RedisData {
    LocalDateTime expireTime;
    Object data;
}
