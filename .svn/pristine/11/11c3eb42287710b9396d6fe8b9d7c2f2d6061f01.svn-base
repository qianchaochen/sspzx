package com.gionee.ssp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wk.ssp.utils.local.ThreadLocalManager;

/**
 * 对本地数据做一些操作，不影响正常流程返回
 * @author dingyw
 *
 * 2017年9月6日
 */
public class LocalInterceptor implements HandlerInterceptor {

	private LocalInterceptor() {

	}

	private static class LocalIntercetorHolder{
		private static LocalInterceptor LOCAL_INTERCEPTOR = new LocalInterceptor();
	}
	
	public static LocalInterceptor instance(){
		return LocalIntercetorHolder.LOCAL_INTERCEPTOR;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ThreadLocalManager.initLocalInfo();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadLocalManager.remove();
	}

	

}
