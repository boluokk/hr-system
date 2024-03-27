package org.boluo.hr.util;

import org.boluo.hr.pojo.Hr;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 🍍
 * @date 2023/10/1
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String && principal.equals("anonymousUser")) {
            return null;
        }
        return (Hr) principal;
    }
}
