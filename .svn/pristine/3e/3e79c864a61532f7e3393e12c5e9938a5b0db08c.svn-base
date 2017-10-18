package com.gionee.ssp.service.adx.impl;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
public class BaseAdxServiceImpl {
	/**
	 * 百度请求url
	 */
	@Value("#{adx_config.baidu_req_url}") 
	protected String baidu_req_url;
	
	/**
	 * 头条请求url
	 */
	@Value("#{adx_config.toutiao_req_url}") 
	protected String toutiao_req_url;
	
	
	/**
	 * inmobi请求url
	 */
	@Value("#{adx_config.inmobi_req_url}") 
	protected String inmobi_req_url;
	
	/**
	 * zaker请求url
	 */
	@Value("#{adx_config.zaker_req_url}") 
	protected String zaker_req_url;
	
	/**
	 * 灵集请求url
	 */
	@Value("#{adx_config.lingji_req_url}") 
	protected String lingji_req_url;
	
	/**
	 * 锁屏是否是测试环境
	 */
	@Value("#{adx_config.lock_debug}") 
	protected String lock_debug;
	
	/**
	 * 音乐头条曝光时长
	 */
	@Value("#{adx_config.music_toutiao_exposure}") 
	protected int music_toutiao_exposure;
	
	/**
	 * 强制曝光时间
	 */
	@Value("#{sdk_config.force_time}") 
	protected int force_time;
	
	/**
	 * 强制曝光时间
	 */
	@Value("#{sdk_config.skip_time}") 
	protected int skip_time;
	
	/**
	 * 允许延迟上报时长
	 */
	@Value("#{sdk_config.delay_time}") 
	protected int delay_time;
	
	/**
	 * 曝光时长
	 */
	@Value("#{sdk_config.exposure_time}") 
	protected int exposure_time;
	
	/**
	 * 不是静默安装的app_id字符串
	 */
	@Value("#{sdk_config.silent_install}")
	protected String silent_install_apps;

}
