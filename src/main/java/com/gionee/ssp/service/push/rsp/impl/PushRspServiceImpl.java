package com.gionee.ssp.service.push.rsp.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.push.rsp.PushRspService;
import com.wk.ssp.vo.AdxType;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月6日
 */
@Service
public class PushRspServiceImpl implements PushRspService{
	
	/**广告ADX提供是直投
	 * @param rsp
	 */
	@Override
	public void setPushRsp(SdkResponseVO rsp){
		if(rsp == null){
			return;
		}
		List<SdkResponseAdVO> list = rsp.getAdms();
		for(SdkResponseAdVO vo : list){
			vo.setAd_cp(AdxType.IPUSH.getAdx());	//0是直投广告
		}
	}

}
