package com.gionee.ssp.common.ipush;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放常量
 *
 */
public final class IpushConstant {

    /** 设置字符集 **/
    public final static String CHARSET = "UTF-8";

    /** 存储地域信息的{@link Map} **/
    public final static Map<String, String> AREA_MAP = new HashMap<String, String>();

    /** {@code DSP}的展示监播{@code URI} **/
    public static String DSP_IMPURL;

    /** {@code DSP}的点击监播{@code URI} **/
    public static String DSP_CLKURL;

    /** {@code DSP}的竞价获胜通知{@code URI} **/
    public static String DSP_WINNOTICEURL;

    /************************************************************
     * 日志用到的key作为常量，方便以后修改
     ************************************************************/

    /** 竞价请求 **/
    public final static String LOG_BID_REQ = "bidreq";
    /** 竞价返回 **/
    public final static String LOG_BID_RES = "bidres";
    /** 计划id **/
    public final static String LOG_OFFER_ID = "offer_id";
    /** 活动id **/
    public final static String LOG_CAMPAIGN_ID = "campaign_id";
    /** {@code ADX}的id **/
    public final static String LOG_ADX_ID = "adx_id";
    /** 佣金比例 **/
    public final static String LOG_COMMISSION_RATE = "commission_rate";
    /** 媒体类型 **/
    public final static String LOG_PUBLISHER_TYPE = "publisher_type";
    /** 地区编号 **/
    public final static String LOG_AREA_CODE = "area_code";
    /** 错误信息 **/
    public final static String LOG_ERROR_MESSAGE = "error_message";
    /** 错误码，错误码为0 表示正常 **/
    public final static String LOG_ERROR_CODE = "error_code";
    /** 时间戳 **/
    public final static String LOG_TIME = "time";

    /************************************************************
     * 以下是ADX的唯一id
     ************************************************************/

    /** 玩咖的{@code ADX}的唯一{@code ID} **/
    public final static String WK_ADX_ID = "1";

    /************************************************************
     * 以下是ES的index和type
     ************************************************************/

    /** ES的index **/
    public final static String ES_INDEX = "ipush";

    /** ES的type **/
    public final static String ES_TYPE = "campaign";

}
