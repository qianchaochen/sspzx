package com.gionee.ssp.service.debug.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gionee.ssp.service.debug.DebugAdContent;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
public class BaseDebugServiceImpl {
	
	/**
	 * debug广告内容
	 */
	@Autowired
	DebugAdContent debugAdContent;
	
	protected String getCreative(String key){
		
		String result = debugAdContent.getDebugAds().get(key);
		int index = result.indexOf(",");
		if(index > -1){
			result = result.substring(0, index);
		}
		return debug_domain + result; //访问测试创意域名
	}
	
	protected List<String> getCreatives(String key){
		 
		String result = debugAdContent.getDebugAds().get(key);
		String[] imgs = result.split(",");
		List<String> img = new ArrayList<>();
		for(int i = 0; i < imgs.length; i++){
			img.add(debug_domain + imgs[i]); //访问测试创意域名
		}
		
		return img;
	}
	protected void setAD_W_H(SdkResponseAdVO adVO, int w, int h){
		adVO.setW(w);
		adVO.setH(h);
	}
	/**
	 * 测试广告访问路径
	 */
	@Value("#{debug_config.DEBUG_DOMAIN}")
	protected String debug_domain;
	
	/**
	 * 测试下载类广告包名
	 */
	@Value("#{debug_config.DEBUG_BUNDLE}")
	protected String debug_bundle;
	
	/**
	 * 测试下载地址
	 */
	@Value("#{debug_config.DEBUG_DOWNLOAD}")
	protected String debug_download_url;

}
