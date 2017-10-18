package com.gionee.ssp.service.debug;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;


/**获取测试广告
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface DebugService {

	/**获取测试广告
	 * @param requestVO
	 * @param data
	 * @return
	 */
	public SdkResponseVO getDebugAd(SdkRequestVO requestVO, FillingDataVO data);
}
