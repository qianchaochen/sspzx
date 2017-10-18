package com.wk.ssp.utils.log;

/**
 * @description: 日志管理工具
 */
public final class WKLogManager {

	private static ThreadLocal<LogInfo> LOG = new ThreadLocal<LogInfo>();

	public static LogInfo initLogInfo(final String ip, final String uri) {
		LogInfo info = new LogInfoImp(ip, uri);
		LOG.set(info);
		return info;
	}	
	
	public static LogInfo getLOG() {
		return LOG.get();
	}
	
	public static void remove(){
		LOG.remove();
	}
	
}

