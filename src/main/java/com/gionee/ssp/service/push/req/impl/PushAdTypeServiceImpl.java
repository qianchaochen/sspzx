package com.gionee.ssp.service.push.req.impl;

import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.push.req.PushAdTypeService;
import com.wk.model.adx.WKSSP.AdSlot;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class PushAdTypeServiceImpl implements PushAdTypeService{
	
	/**
	 * @return
	 */
	public boolean isSpashScreen(AdSlot adSlot){
		int is_splash_screen = adSlot.getIsSplashScreen();
		if(is_splash_screen == CommonConstant.IS_TRUE.TRUE.getValue() && adSlot.hasBanner()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isInsertScreen(AdSlot adSlot) {
		int is_splash_screen = adSlot.getIsSplashScreen();
		int instl = adSlot.getInstl();
		if(is_splash_screen == CommonConstant.IS_TRUE.FALSE.getValue() 
        		&& instl == CommonConstant.IS_TRUE.TRUE.getValue() 
        		&& adSlot.hasBanner()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isBanner(AdSlot adSlot) {
		return adSlot.hasBanner();
	}

}
