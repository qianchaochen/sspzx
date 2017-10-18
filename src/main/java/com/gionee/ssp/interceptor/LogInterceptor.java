package com.gionee.ssp.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.utils.log.WKLogManager;
/**
 * @author dingyw
 *
 * 2017年9月6日
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
	
    private Logger logger = LogManager.getLogger();
    private Marker bidLogMarker = MarkerManager.getMarker("BidLog"); 
	//单例实现初始化
	private static class LogInterceptorHolder{
		private final static LogInterceptor INSTANCE = new LogInterceptor();
	}
	
	private LogInterceptor() {}
	//获取实例对象
	public static LogInterceptor instance(){
		return LogInterceptorHolder.INSTANCE;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String ip = WKHttpUtils.getIpAddr(request);//request.getRemoteAddr(); //获取IP地址，正式环境用request.getHeader("X-Forwarded-For");
		String uri = request.getRequestURI();
		WKLogManager.initLogInfo(ip, uri);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		//如果是请求广告的日志，才会写到请求广告的日志文件中，其它的不需要写 by dingyw 2017-10-11
		if(WKLogManager.getLOG().isReqAdLog()){
		    logger.info(bidLogMarker, WKLogManager.getLOG().getReqAdLog());
		}
		//落系统错误日志
        String sysError = WKLogManager.getLOG().getSysErrorLog();
        if (!StringUtils.isBlank(sysError)) {
	        logger.error(sysError);
        }

		//从线程中移除
        WKLogManager.remove();
	}

}
