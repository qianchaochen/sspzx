package com.gionee.ssp.service.baidu.impl;

import org.springframework.beans.factory.annotation.Value;

import com.gionee.ssp.service.adx.impl.BaseAdxServiceImpl;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
public class BaseBaiduServiceImpl extends BaseAdxServiceImpl{
	
	/**
	 * 百度接口版本
	 */
	@Value("#{baidu_config.BAIDU_API_VERSION}") 
	protected String baidu_api_version;
	
	/**
	 * 百度密钥路径
	 */
	@Value("#{baidu_config.baidu_privateKey_path}") 
	protected String baidu_privateKey_path;
	
	/**
	 * 百度key
	 */
	@Value("#{baidu_config.baidu_accessKey}") 
	protected String baidu_accessKey;
	
	
	/**
	 * 百度广告请求uri
	 */
	@Value("#{baidu_config.baidu_req_ad_uri}") 
	protected String baidu_req_ad_uri;

}
