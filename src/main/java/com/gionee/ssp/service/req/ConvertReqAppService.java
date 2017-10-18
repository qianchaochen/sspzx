package com.gionee.ssp.service.req;

import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ConvertReqAppService {
	/**构建app信息
	 * @param builder
	 * @param sdkReq
	 * @param vo
	 */
	public void setApp(Builder builder, SdkRequestVO sdkReq, FillingDataVO vo);

}
