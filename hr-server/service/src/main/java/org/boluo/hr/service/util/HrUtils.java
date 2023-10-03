package com.boluo.hr.service.util;

import org.boluo.hr.pojo.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author @1352955539(boluo)
 * @date 2021/1/27 - 16:46
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
