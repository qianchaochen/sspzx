package com.gionee.ssp.service.ad.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.ad.AdInfoService;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.FillingDataVO;


/**广告位信息服务层
 * @author dingyw
 *
 * 2017年9月5日
 */
@Service
public class AdInfoServiceImpl implements AdInfoService {	

	private final String BID_SWITCH_ON = "0";
	
	@Override
	public int getAdType(FillingDataVO vo) {
		
		if(null != vo.getNativ()){
			return CommonConstant.SDK_AD_TYPE.NATIVE.getValue();
		}
			
		if(0 != vo.getIs_splash()){
			return CommonConstant.SDK_AD_TYPE.SPLASH_SCREEN.getValue();
		}
		if(0 != vo.getInstl()){
			return CommonConstant.SDK_AD_TYPE.INSERT_SCREEN.getValue();
		}
		return CommonConstant.SDK_AD_TYPE.BANNER.getValue();
	}
	
	@Override
	public boolean isIpush(FillingDataVO vo) {
		String bidSwitch = vo.getBid_switch();
		
		//如果没有设置bidSwitch的，默认为先请求直投再竞价
		if(StringUtils.isEmpty(bidSwitch)){
			bidSwitch = "0";
		}
		
		WKLogManager.getLOG().addReqAdLog("bid_switch", bidSwitch);
		return !BID_SWITCH_ON.equals(bidSwitch);
	}
	
}
