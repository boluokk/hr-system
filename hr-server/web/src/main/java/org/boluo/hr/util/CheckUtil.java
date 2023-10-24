package org.boluo.hr.util;

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

    public static boolean hasLength(Integer[] target) {
        return target != null && target.length > 0;
    }
}
