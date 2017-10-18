package com.gionee.ssp.service.baidu;

import com.gionee.ssp.service.broadcast.AdBroadcastService;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2016年12月15日
 */
public interface BaiduAdService extends AdBroadcastService{
	
	/**获取百度广告
	 * @param requestVO
	 * @param fillingDataVO
	 * @return
	 * @throws Exception
	 */
	public SdkResponseVO getAd(SdkRequestVO requestVO,FillingDataVO fillingDataVO) throws Exception;

}
