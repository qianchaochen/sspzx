package com.gionee.ssp.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SSPMethodInterceptor implements MethodInterceptor {
	private Logger logger = LogManager.getLogger();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		logger.info("--Log By Cenwei -----------------------------------------------------------------------------");
		logger.info(invocation.getMethod() + ":BEGIN!--(Cenwei Log)");// 方法前的操作
		Object obj = invocation.proceed();// 执行需要Log的方法
		logger.info(invocation.getMethod() + ":END!--(Cenwei Log)");// 方法后的操作
		logger.info("-------------------------------------------------------------------------------------------------");
		return obj;
	}
}
