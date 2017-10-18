package com.gionee.ssp.service.adFlow.impl;

import org.springframework.stereotype.Service;

import com.gionee.ssp.conf.gameAndStore.GameAppSettings;
import com.gionee.ssp.service.adFlow.AdFlowModeFilterService;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年4月22日
 */
@Service
public class AdFlowModeFilterServiceImpl implements AdFlowModeFilterService{
	
	@Override
	public boolean doFilter(FillingDataVO fillingDataVO, SdkRequestVO requestVO) {
		
		return false;
	}

	@Override
	public boolean doFilterPackage(FillingDataVO fillingDataVO,SdkRequestVO requestVO) {
		//如果是游戏大厅/应用商店，无论如何都将请求发送给金立自有adx
		if(GameAppSettings.CHANNEL.APP_STORE.bundle.equals(fillingDataVO.getBundle())||GameAppSettings.CHANNEL.GAME_HALL.bundle.equals(fillingDataVO.getBundle())){
			return true;
		}
		return false;
	}

	@Override
	public boolean doFilterAppId(FillingDataVO fillingDataVO,SdkRequestVO requestVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doFilterAdSlotId(FillingDataVO fillingDataVO,SdkRequestVO requestVO) {
		// TODO Auto-generated method stub
		return false;
	}




}
