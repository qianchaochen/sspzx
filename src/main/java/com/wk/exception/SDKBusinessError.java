package com.wk.exception;

import java.util.Map;

/**
 * @description: 业务错误码定义
 */
public class SDKBusinessError extends Errors {

	protected static Map<Integer, String> SDK_ERROR_MESSAGE;
	
	public static String MISSING_SVR = "50";
	
	public static String MISSING_DEVICE = "51";
	
	public static String MISSION_CUID = "52";
	
	public static String MISSING_CLIENT_ID = "53";
	
	public static String MISSING_DEVICE_ID = "54";

	public static String ERROR_FORMAT_REQUEST = "100000";
	public static String MISSING_REQUEST_ID = "101000";
	public static String MISSING_API_VERSION = "102000";
	public static String ERROR_API_FORMAT = "102010";
	public static String MISSING_APP_INFO = "103000";
	public static String MISSING_APP_ID = "103010";
	public static String ERROR_APP_ID = "103011";
	public static String DISABLED_APP_ID = "103012";
	public static String ERROR_CHANNEL_ID = "103020";
	public static String MISSING_APP_VERSION = "103030";
	public static String MISSING_DEVICE_INFO = "104000";
	public static String MISSING_DEVICE_TYPE = "104010";
	public static String ERROR_DEVICE_TYPE = "104011";
	public static String MISSING_OS_TYPE = "104020";
	public static String ERROR_OS_TYPE = "104021";
	public static String MISSING_OS_VERSION = "104030";
	public static String ERROR_OS_VERSION = "104031";
	public static String MISSING_VENDOR = "104050";
	public static String MISSING_MODEL = "104060";
	public static String MISSING_ANDROID_ID = "104070";
	public static String ERROR_ANDROID_ID = "104071";
	public static String MISSING_IMEI_MD5 = "104080";
	public static String MISSING_MAC = "104090";
	public static String ERROR_MAC = "104091";
	public static String MISSING_SCREEN_SIZE_WIDTH = "104100";
	public static String MISSING_SCREEN_SIZE_HEIGHT = "1040110";
	public static String MISSING_NETWORT_INFO = "105000";
	public static String MISSING_IPV4 = "105010";
	public static String MISSING_FORMAT_IPV4 = "105011";
	public static String MISSING_CONNECTION_TYPE = "105020";
	public static String ERROR_CONNECTION_TYPE = "105021";
	public static String MISSING_OPERATOR_TYPE = "105030";
	public static String ERROR_OPERATOR_TYPE = "105031";
	public static String MISSING_COORDINATE_TYPE = "106000";
	public static String ERROR_CORRODINATE_TYPE = "106001";
	public static String MISSING_LONGETUDE = "106010";
	public static String MISSING_LATITUDE = "106020";
	public static String MISSING_GPS_TEMESTAMP = "106030";
	public static String MEISSING_ADSLOT = "107000";
	public static String MISSING_ADSLOT_ID = "107010";
	public static String ERROR_ADSLOT_ID = "107011";
	public static String DISABLED_ADSLOT_ID = "107012";
	public static String NOT_MATCH_ADSLOT_ID = "107013";
	public static String MISSING_ADSLOT_SIZE_WIDTH = "107020";
	public static String MISSING_ADSLOT_SIZE_HEIGHT = "107030";

}
