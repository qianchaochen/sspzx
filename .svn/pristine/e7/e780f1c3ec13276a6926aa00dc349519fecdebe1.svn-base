package com.wk.ssp.utils.log;

import org.apache.commons.lang3.StringUtils;

import com.wk.ssp.utils.DateTimeUtils;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
final class LogInfoImp extends LogInfo {

	boolean is_error = false;
	boolean is_sysError = false;
	boolean is_noContent = false;

	/**
	 * 构造方法，必须传入ip，如果没有ip请传入"null"
	 */
	public LogInfoImp(final String ip, final String uri) {
		super(ip, uri);
	}

	/**
	 * 设置请求的id
	 */
	@Override
	public LogInfo addrequestId(String requestId, LogLevel level) {	
		StringBuilder info = null;
		if (LogLevel.INFO.equals(level)) {
			info = reqAdLogInfo;
		} else if (LogLevel.SYSERROR.equals(level)) {
			info = sysLogInfo;
		} else {
			return this;
		}
		info.append("[request_id:").append(requestId).append("]");
		return this;
	}

	/**
	 * 增加请求日志信息，按照key-value形式
	 */
	@Override
	public LogInfo addReqAdLog(final String key, final String value) {
		reqAdLogInfo.append("[").append(key).append(":").append(value).append("]");
		return this;
	}
	/**
	 * 获取请求广告的日志
	 */
	@Override
	public String getReqAdLog() {
		if (!is_error && !is_noContent) {
			reqAdLogInfo.append("[is_error:0]");
		}
		this.addReqAdLog("time", String.valueOf(System.currentTimeMillis()));
		long useTime = DateTimeUtils.getCurrentMillis() - startTime;
		this.addReqAdLog("process_time", String.valueOf(useTime));
		return ip + "  " + uri + " " + "ALERT:" +reqAdLogInfo.toString();
	}

	/**
	 * 系统错误的toString 方法
	 */
	@Override
	public String getSysErrorLog() {
		if (is_sysError) {
			return sysLogInfo.toString();
		}
		return "";
	}
	/**
	 * 在日志中添加错误信息
	 */
	@Override
	public LogInfo addErrorLog(final String e) {
		is_error = true;
		reqAdLogInfo.append("[is_error:1]")//
				.append("[error_message:").append(e).append("]");
		return this;
	}

	/**
	 * 获取{@code ip}
	 */
	public String getIp() {
		return this.ip;
	}
	@Override
	public void setIp(String ip) {
		this.ip=ip;	
	}
	

	@Override
	public LogInfo addNoContent(){
		is_noContent = true;
		reqAdLogInfo.append("[is_error:6]");
		return this;
	}
	/**
	 * 在日志中添加错误信息
	 */
	@Override
	public LogInfo addSysErrorLog(final Exception e) {
		is_sysError = true;
		
		String msg = "null";
		
		if(null != e){
			StringBuffer  sb  = new StringBuffer(""); 
			String message = e.toString();
			int length = e.getStackTrace().length;
			
			sb.append(message + "\n");  
			if (length > 0) {  
                for (int i = 0; i < length; i++) {  
                	sb.append("\t" + e.getStackTrace()[i] + "\n");  
                }  
            }
			msg = sb.toString();
		}
		
		sysLogInfo.append("[error_message:").append(msg).append("]").append("[time:")
				.append(DateTimeUtils.getCurrentSecond()).append("]");
		return this;
	}

	@Override
	public boolean isReqAdLog() {
		//判断依据，是否有往reqAdLogInfo中写日志,add by dingyw 2017-10-12
		if(StringUtils.isEmpty(reqAdLogInfo.toString())){
			return false;
		}
		return true;
	}
}
