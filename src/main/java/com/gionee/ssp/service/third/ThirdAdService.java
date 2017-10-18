package com.gionee.ssp.service.third;

import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**第三方的广告服务层
 * @author dingyw
 *
 * 2017年4月19日
 */
public interface ThirdAdService {
	/**
	 * @param adxInfoVO
	 * @param sdkResponseVO
	 * @param fillingDataVO
	 * @param requestVO
	 * @throws Exception
	 */
	public SdkResponseVO getThirdAd(AdxInfoVO adxInfoVO,SdkRequestVO requestVO,FillingDataVO fillingDataVO) throws Exception;

}
