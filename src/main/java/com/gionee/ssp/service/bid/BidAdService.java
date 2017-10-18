package com.gionee.ssp.service.bid;

import com.gionee.ssp.service.broadcast.AdBroadcastService;
import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年4月19日
 */
public interface BidAdService extends AdBroadcastService{
	
	/**竞价广告服务请求
	 * @param adx
	 * @param ADXRequest
	 * @param response
	 * @param sdkResponseVO
	 * @param vo
	 * @throws Exception
	 */
	public SdkResponseVO getAd(AdxInfoVO adx, SdkRequestVO req,FillingDataVO vo) throws Exception;


}
