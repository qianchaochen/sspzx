package com.gionee.ssp.service.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.wk.ssp.utils.DataUtils;

/**
 * 屏幕设备适配器
 * 插屏adapter
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class InstlAdapter {
	
	Map<String, String> adapter;
	
	@PostConstruct
	public void init(){
		adapter = new HashMap<String, String>();
		DataUtils.sizeAdapterInit(adapter, new ClassPathResource("instl.properties"));
	}
	public Map<String, String> getAdapter(){
		return adapter;
	}

}
