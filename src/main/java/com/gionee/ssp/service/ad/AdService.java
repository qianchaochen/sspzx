package com.gionee.ssp.service.ad;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**广告服务层
 * @author dingyw
 *
 * 2017年4月19日
 */
public interface AdService {
	
	/**
	 * @param fillingDataVO
	 * @param requestVO
	 * @param sdkResponseVO
	 * @param isIpush
	 * @param requestId
	 */
	public SdkResponseVO getAd(FillingDataVO fillingDataVO,SdkRequestVO requestVO,String requestId) throws Exception;

}
