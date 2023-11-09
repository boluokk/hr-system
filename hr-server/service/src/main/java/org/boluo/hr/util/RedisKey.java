package org.boluo.hr.util;

import org.springframework.stereotype.Component;

/**
 * @author boluo
 * @date 2023/11/04
 */
@Component
public class RedisKey {
    public static final String HR_ONLINE_COUNT_KEY = "hr_online_key";
    public static final String HR_LOGIN_COUNT_KEY = "hr_login_key";
    public static final String HR_RECORD_COUNT_KEY = "hr_record_key";
    public static final String HR_REWARD_PUNISHMENT_COUNT_KEY = "hr_reward_punishment_key";
}
