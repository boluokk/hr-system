package org.boluo.hr.employee.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.boluo.hr.employee.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * token 工具类
 *
 * @author boluo
 * @date 2024/01/29
 */
public class TokenUtil {
    private static final int exp = 1000 * 60 * 60 * 24;
    private static final byte[] key = "jwtKey".getBytes();

    /**
     * 获取token
     *
     * @param user 用户信息
     * @return token 字符串
     */
    public static String getToken(User user) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(JWTPayload.EXPIRES_AT, System.currentTimeMillis() + exp);
        payload.put("user", user);
        return JWTUtil.createToken(payload, key);
    }

    /**
     * token 是否过期
     *
     * @param jwt JWT
     * @return 结果
     */
    public static boolean isExpired(JWT jwt) {
        return !jwt.setKey(key).validate(0);
    }

    /**
     * token 完整性检查
     *
     * @param jwt JWT
     * @return 结果
     */
    public static boolean badToken(JWT jwt) {
        return !jwt.setKey(key).verify();
    }
}
