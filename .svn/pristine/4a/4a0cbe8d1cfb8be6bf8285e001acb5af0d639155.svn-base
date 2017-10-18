package com.gionee.ssp.service.req.impl;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.req.ConvertReqSdkService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.sdk.SDKInfoVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqSdkServiceImpl implements ConvertReqSdkService{
	

	/**转化SDK信息
	 * @param reqBuilder
	 * @param sdkInfoVO
	 */
	public void setSDKInfo(Builder reqBuilder, SDKInfoVO sdkInfoVO) {

		WKSSP.SDK.Builder sdkBuilder = WKSSP.SDK.newBuilder();

		sdkBuilder.setSvr(sdkInfoVO.getSvr());
		sdkBuilder.setDevice(sdkInfoVO.getDevice());
		sdkBuilder.setCuid(sdkInfoVO.getCuid());
		sdkBuilder.setClientId(sdkInfoVO.getClient_id());
		sdkBuilder.setDeviceId(sdkInfoVO.getDevice_id());
		sdkBuilder.setApilevel(sdkInfoVO.getApilevel());

		reqBuilder.setSdk(sdkBuilder);
	}

}
