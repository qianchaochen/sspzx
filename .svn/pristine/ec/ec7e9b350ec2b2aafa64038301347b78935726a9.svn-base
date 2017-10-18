package com.wk.ssp.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

/**
 *
 * {@code Object}工具类
 *
 */
public final class WKObjectUtils {

    /**
     * 将{@link String}类型的版本号格式化成{@link int}类型的版本号
     * 
     * @param {@link String}类型的版本号
     * @return 返回{@link int}类型的版本号
     */
    public static int formatString2Int4Version(String osv) {
        if (StringUtils.isBlank(osv)) {
            return 0;
        }
        String[] osvs = StringUtils.split(osv, "\\.");

        if (osvs.length == 1) {
            return toInt(osvs[0]) * 100;
        } else {
            return toInt(osvs[0]) * 100 + toInt(osvs[1]) * 10;
        }
    }

    /**
     * 将{@link String}类型字符串转化成{@link int}类型
     * 
     * @param str {@link String}类型字符串
     * @return 返回@link int}类型
     */
    public static int toInt(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    /**
     * 将{@link Object}类型字符串转化成{@link int}类型
     * 
     * @param str {@link Object}类型字符串
     * @return 返回@link int}类型
     */
    public static int toInt(Object ob) {
        if (ObjectUtils.isEmpty(ob)) {
            return 0;
        }

        return Integer.parseInt(ob.toString());
    }

    /**
     * 将{@link String}类型字符串转化成{@link Double}类型
     * 
     * @param str {@link String}类型字符串
     * @return 返回@link double}类型
     */
    public static double toDouble(String str) {
        if (StringUtils.isBlank(str)) {
            return 0D;
        }
        return Double.parseDouble(str);
    }

    /**
     * 将{@link Object}类型字符串转化成{@link Double}类型
     * 
     * @param str {@link Object}类型字符串
     * @return 返回@link double}类型
     */
    public static double toDouble(Object ob) {
        if (ObjectUtils.isEmpty(ob)) {
            return 0D;
        }

        return Double.parseDouble(ob.toString());
    }
}
