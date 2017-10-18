package com.gionee.ssp.service.sdk.rsp.impl;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
public class BaseSdkSspServiceImpl {
	/**
	 * 展示类广告监播发送时机。1：页面开始加载时发送，2：页面加载完成时发送
	 */
	@Value("#{tracker_config.IMP_TRACKER_OPERATE_TIME}")
	protected int imp_tracker_operate_time;

	
	/**
	 * 上传崩溃日志开关
	 */
	@Value("#{tracker_config.BREAKDOWN_SWICH}")
	protected int break_down_swich;

	/**
	 * 请求横幅超时时间
	 */
	@Value("#{ad_timeout_config.RQTO_BANNER}")
	protected String RQTO_BANNER_TIMEOUT;
	
	/**
	 * 请求插屏超时时间
	 */
	@Value("#{ad_timeout_config.RQTO_INSTL}")
	protected String RQTO_INSTL_TIMEOUT;
	
	/**
	 * 请求开屏超时时间
	 */
	@Value("#{ad_timeout_config.RQTO_SPLASH}")
	protected String RQTO_SPLASH_TIMEOUT;
	
	/**
	 * 请求原生超时时间
	 */
	@Value("#{ad_timeout_config.RQTO_NATIVE}")
	protected String RQTO_NATIVE_TIMEOUT;

	
	
	/**
	 * rander横幅超时时间
	 */
	@Value("#{ad_timeout_config.RDTO_BANNER}")
	protected int RDTO_BANNER_TIMEOUT;

	
	/**
	 * rander插屏超时时间
	 */
	@Value("#{ad_timeout_config.RDTO_INSTL}")
	protected int RDTO_INSTL_TIMEOUT;
	
	/**
	 *rander开屏超时时间 
	 */
	@Value("#{ad_timeout_config.RDTO_SPLASH}")
	protected int RDTO_SPLASH_TIMEOUT;
	
	/**
	 * rander原生超时时间 
	 */
	@Value("#{ad_timeout_config.RDTO_NATIVE}")
	protected int RDTO_NATIVE_TIMEOUT;
	
	/**
	 * 自升级开关标志
	 */
	@Value("#{const_config.AUTO_UPDATE_SWICH}")
	protected int AUTO_UPDATE_SWICH;
	/**
	 * 下载二次确认开关标志
	 */
	@Value("#{const_config.DOWN_CONFIRM_TWICE_SWICH}")
	protected int DOWN_CONFIRM_TWICE_SWICH;
	
	/**
	 * logo显示开关
	 */
	@Value("#{const_config.LOGO_SWICH}")
	protected int LOGO_SWICH;

}
