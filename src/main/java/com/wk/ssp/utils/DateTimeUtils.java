package com.wk.ssp.utils;

import java.util.Calendar;

/**
 * <p>
 * Title: 时间工具类<／p>
 *
 */
public final class DateTimeUtils {

    /**
     * 拿到当前时间戳，精确到毫秒
     * 
     * @return
     */
    public static Long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 拿到当前时间戳，精确到秒
     * 
     * @return
     */
    public static Long getCurrentSecond() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间，时间格式是星期的前三位英文字母加当前小时
     * 
     * @return 返回星期的前三位英文字母加当前小时的时间
     */
    public static int getTime() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return (dayOfWeek - 1) * 24 + hour;
    }

}
