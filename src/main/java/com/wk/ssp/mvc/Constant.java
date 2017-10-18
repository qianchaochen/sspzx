package com.wk.ssp.mvc;

/**
 * @description: 常量定义
 */
public class Constant {
	
    /** 设置字符集 **/
    public final static String CHARSET = "UTF-8";
	
	/** ADX注册的标识sspid **/
	public static final String SSPID = "id";
	
	/** ADX注册的令牌token **/
	public static final String TOKEN = "token";
	
	/** 流量分配精度 **/
	public static final int PRECISION = 1000;
	
	/** 默认流量比例 **/
	public static final double DEFUALT_PERCENT = 0.5;
	
	/** redis存储应用信息和广告位信息键值常量 **/
	public static final String REDIS_KEY = "wk_SSP_";
	
	/** redis存储锁屏请求广告的广告图片url KEY **/
	public static final String REDIS_STORY_LOCKER_USED_URLS = "STORY_LOCKER_USED_URLS_";
	
	/**SSP平台广告屏蔽配置KEY**/
	public static final String SSP_AD_SHIELD_CFG = "AD_SHIELD_CFG";
	
	/** redis存储流量分配比例键值常量 **/
	public static final String PERCNET_KEY = "wk_SSP_percent";
	
	/** 目标ADX名称 **/
	public static final String TARGET_ADX = "target_adx";
	
	/** 同一view下自动请求广告间隔 **/
	public static final int VIEW_INTERVAL = 600;
	
	/** 同一view下重复请求广告次数 add by dingyw **/
	public static final int VIEW_INTERVAL_TIMES = 3;
	
	/** 应用未通过审核 **/
	public static final int APP_NOT_PASS_AUDIT = 0;
	
	/** 测试ADXID **/
	public static final String DEBUG_TARGET_ADX_ID = "100";
	
	/** 广告尺寸适配默认设备屏幕尺寸**/
	public static final String ADAPTER_DEFULE = "other";
	
	/** 广告位宽 **/
	public static final String ADSLOT_WIDTH = "width";
	
	/** 广告位高 **/
	public static final String ADSLOT_HIGH = "high";

	/** 打点开关开启 **/
	public static final String RBI_FATAL_ON = "1";
	
	/** 打点开关关闭 **/
	public static final String RBI_FATAL_OFF = "0";

    /** 反作弊策略，请求上限单位时间。 单位：毫秒 **/
    public static final long ANTI_TIMER = 1000;

    /** 扫描作弊黑名单的时间间隔 **/
    public static final long ANTI_BLACKLIST_SCAN_INTERVAL = 3600 * 1000L;

    /** 反作弊功能开启标识 **/
    public static final String ANTI_ON = "1";

    /** 原生广告规范版本 **/
    public static final String NATIVE_VER = "1.1";

    /** sdk请求广告数据参数名 **/
    public static final String REQUEST_PARAM_REQJSON = "reqjson";
    
    /** 默认imei号 **/
    public static final String DEFAULT_IMEI = "000000000000000";
    
    /** 默认api接口渠道ID **/
    public static final String DEFAULT_CHANNELID = "default";
    
    /** 广告过期时间 **/
    public static final int AD_EXPIRATION_TIME = 86400;

    /** dmp redis存储键值 **/
    public static final String DMP_KEY = "ad_targeted_tags_${imeimd5}";
    
    //sdk错误码
    public static final int SDK_RSP_NO_ERROR = 0;
    
    //sdk1.7.7版本需要以h5地址为准，1.7.7及以下版本interaction_type固定为1
    public static String SDK_VERSION_1_7_7 = "1.7.7";
    
    
}
