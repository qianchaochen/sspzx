package com.gionee.ssp.service.req;

import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ConvertReqDeviceService {
	
	/**构建device信息
	 * @param reqBuilder
	 * @param sdkReq
	 * @throws Exception
	 */
	public void setDevice(Builder reqBuilder, SdkRequestVO sdkReq) throws Exception;

}
