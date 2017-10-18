package com.gionee.ssp.service.debug;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.wk.ssp.utils.DataUtils;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
@Component
public class DebugAdContent {
	
	Map<String, String> DEBUG_ADS = new HashMap<>();
	
	@PostConstruct
	public void init(){
		DataUtils.initMapFromProperties(DEBUG_ADS, new ClassPathResource("debug-creative.properties"));
	}
	public Map<String, String> getDebugAds(){
		return DEBUG_ADS;
	}

}
