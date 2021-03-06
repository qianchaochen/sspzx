package com.gionee.ssp.service.lingji;

import com.gionee.ssp.service.broadcast.AdBroadcastService;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface LingjiAdService extends AdBroadcastService{

	/**获取灵集广告
	 * @param requestVO
	 * @param fillingDataVO
	 * @return
	 * @throws Exception
	 */
	public SdkResponseVO getAd(SdkRequestVO requestVO,FillingDataVO fillingDataVO) throws Exception;
}
