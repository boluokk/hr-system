package org.boluo.hr.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 🍍
 * @date 2023/10/1
 */
public class PasswordEncoder {

    private final static BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
    public static String encode(String password) {
        return bCrypt.encode(password);
    }
}
