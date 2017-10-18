package com.gionee.ssp.service.adx.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.conf.gameAndStore.GameAppSettings;
import com.gionee.ssp.service.adx.AdxSelectService;
import com.gionee.ssp.service.adx.GetAdxNameService;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;

/** 广告流量控制服务层
 * @author dingyw
 *
 * 2017年4月19日
 */
@Service
public class AdxSelectServiceImpl implements AdxSelectService{
	
	
	/**
	 * adx参数缓存
	 */
	@Resource(name="adx_map")
	public Map<String, AdxInfoVO> adx_map;
	
	/**
	 * 获取adx的名字服务层
	 */
	@Autowired
	GetAdxNameService getAdxNameService;
	
	@Override
	public AdxInfoVO getADXDestination(FillingDataVO vo) throws Exception {
		
		String adxName;
		
		//如果是游戏大厅/应用商店，无论如何都将请求发送给金立自有adx
		if(GameAppSettings.CHANNEL.APP_STORE.bundle.equals(vo.getBundle())||GameAppSettings.CHANNEL.GAME_HALL.bundle.equals(vo.getBundle())){
			adxName = CommonConstant.SYS_CODE.FIRM_NAME.getValue();
		}else{		
			adxName = getAdxNameService.getADXName(vo.getPercent());
		}
		
		if(CommonConstant.SYS_CODE.FIRM_NAME.getValue().equals(adxName) || CommonConstant.SYS_CODE.WK_NAME.getValue().equals(adxName)){ //需要竞价的
			AdxInfoVO adxInfoVO= adx_map.get(adxName);
			adxInfoVO.setAdxName(adxName);
			return adxInfoVO;
			
		}else{ //第三方
			return new AdxInfoVO(adxName);
		}
	}
	
}
