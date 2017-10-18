package com.gionee.ssp.service.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.wk.ssp.utils.DataUtils;

/**屏幕设备适配器
 * 横幅适配
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class BannerAdapter {
	
	Map<String, String> adapter;
	
	@PostConstruct
	public void init(){
		adapter = new HashMap<String, String>();
		DataUtils.sizeAdapterInit(adapter, new ClassPathResource("banner.properties"));
	}
	public Map<String, String> getAdapter(){
		return adapter;
	}

}
