package org.boluo.hr.util;

import org.boluo.hr.pojo.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 🍍
 * @date 2023/10/1
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
