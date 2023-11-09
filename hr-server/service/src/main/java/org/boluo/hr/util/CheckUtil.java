package org.boluo.hr.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.Set;

/**
 * 检查工具
 *
 * @author 🍍
 * @date 2023/10/23
 */
public class CheckUtil {
    public static boolean isNull(Object target) {
        return target == null;
    }

    public static boolean isEmpty(byte[] target) {
        return target == null || target.length == 0;
    }

    public static boolean isEmpty(Object[] target) {
        return target == null || target.length == 0;
    }

    public static boolean hasLength(Integer[] target) {
        return target != null && target.length > 0;
    }

    public static boolean hasLength(String target) {
        return target != null && !target.isEmpty();
    }

    public static boolean isNotNull(Object target) {
        return target != null;
    }
}
