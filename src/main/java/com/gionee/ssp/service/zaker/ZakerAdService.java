package com.gionee.ssp.service.zaker;

import com.gionee.ssp.service.broadcast.AdBroadcastService;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 
 * @description 
 * @author wuxing
 * @date 2017年4月5日
 *
 */
public interface ZakerAdService extends AdBroadcastService{
	
	/**获取zaker广告
	 * @param requestVO
	 * @param fillingDataVO
	 * @return
	 * @throws Exception
	 */
	public SdkResponseVO getAd(SdkRequestVO requestVO,FillingDataVO fillingDataVO) throws Exception;

}
