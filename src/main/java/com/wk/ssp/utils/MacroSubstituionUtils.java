package com.wk.ssp.utils;

import com.wk.ssp.mvc.ipush.es.vo.QueryVO;

/**
 *
 * 宏替换工具类
 *
 */
public class MacroSubstituionUtils {

    /**
     * 对第三方检测的监播进行替换，对于没有的信息可以不进行替换
     * 
     * @param url 第三方检测地址
     * @param queryVO 宏替换信息
     * @return 返回替换后的第三方检测地址
     */
    public static String macroSubstitutionMMA(final String url, final QueryVO queryVO) {
        String result = url;

        int os = 3;
        if (queryVO.getOs().toLowerCase().equals("android")) {
            os = 0;
        } else if (queryVO.getOs().toLowerCase().equals("ios")) {
            os = 1;
        } else if (queryVO.getOs().toLowerCase().equals("Windows Phone")) {
            os = 2;
        }
        result = result.replaceAll("__OS__", os + "") // 系统版本
                .replaceAll("__IMEI__", queryVO.getImeiMd5().toLowerCase()) // imei取MD5值
                .replaceAll("__IP__", queryVO.getIp()) // 客户端IP地址
                .replaceAll("__ANDROIDID__", queryVO.getDpidMd5().toLowerCase()); // android去MD5摘要

        return result;
    }
}
