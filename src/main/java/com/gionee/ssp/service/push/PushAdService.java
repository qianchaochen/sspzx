package com.gionee.ssp.service.push;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;


/**获取直投广告
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface PushAdService {
	
	/**获取直投广告
	 * @param wKSSPRequest
	 * @param requestVO
	 * @param requestId
	 * @param fillingDataVO
	 * @return
	 * @throws Exception
	 */
	public SdkResponseVO getPushAd(final SdkRequestVO requestVO,
            final String requestId,FillingDataVO fillingDataVO) throws Exception;
}
