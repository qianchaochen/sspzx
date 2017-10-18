package com.wk.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:基础错误类型
 */
public class Errors {

	protected static Map<Integer, String> ERROR_MESSAGE;
	protected static Map<Integer, Integer> HTTP_CODE;
	
	public static int NO_ERROR = 0;
	public static int INTERNAL_ERROR = 1;
	public static int PARAM_ERROR = 2;
	public static int API_UNSUPPORTED = 3;
	public static int ABOLITION_API = 4;
	public static int VERIFICATION_FAILURE = 5;
	public static int NO_CONTENT = 6; //无内容
	public static int IN_BLACKLIST = 7;
	public static int LOWER_API_VERSION = 8;

	static {
		ERROR_MESSAGE = new HashMap<Integer, String>();
		ERROR_MESSAGE.put(NO_ERROR, "no error");
		ERROR_MESSAGE.put(INTERNAL_ERROR, "internal error");
		ERROR_MESSAGE.put(API_UNSUPPORTED, "Unsupported api");
		ERROR_MESSAGE.put(PARAM_ERROR, "param error");
		ERROR_MESSAGE.put(ABOLITION_API, "abolished api");
		ERROR_MESSAGE.put(VERIFICATION_FAILURE, "verification failure");
		ERROR_MESSAGE.put(NO_CONTENT, "no content");

		HTTP_CODE = new HashMap<Integer, Integer>();
		HTTP_CODE.put(INTERNAL_ERROR, 500);
		HTTP_CODE.put(API_UNSUPPORTED, 403);
		HTTP_CODE.put(PARAM_ERROR, 403);
		HTTP_CODE.put(ABOLITION_API, 403);
		HTTP_CODE.put(VERIFICATION_FAILURE, 403);
		HTTP_CODE.put(NO_CONTENT, 204);
		HTTP_CODE.put(NO_ERROR, 200);
	}

	public static String getErrorMessage(int key) {
		return ERROR_MESSAGE.get(key);
	}

	public static int getHTTP_CODE(int key) {
		return HTTP_CODE.get(key);
	}
}
