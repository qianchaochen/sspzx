package com.gionee.ssp.service.antiCheating;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**IP防刷策略参数以及内容
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class AntiCheatingContent {
	/** 用户访问次数 **/
	public static ConcurrentHashMap<String, AtomicInteger> ACCESS_RECORD = new ConcurrentHashMap<String, AtomicInteger>();;
	
	/** 用户黑名单 **/
	public static Map<String, Long> BLACKLIST = new HashMap<String, Long>();;
		
	/** IP被封间隔 **/
	public static int FORBID_TIME;
	
	public static ConcurrentHashMap<String, AtomicInteger> getAccessRecord() {
		return ACCESS_RECORD;
	}
	
	public static Map<String, Long> getBlacklist() {
		return BLACKLIST;
	}

	@Value("#{anti_cheating_config.FORBID_TIME}")
	public void setConfig_FORBID_TIME(int fORBID_TIME) {
		AntiCheatingContent.FORBID_TIME = fORBID_TIME;
	}
	
	

}
