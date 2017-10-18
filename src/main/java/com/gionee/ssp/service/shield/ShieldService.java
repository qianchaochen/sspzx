package com.gionee.ssp.service.shield;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface ShieldService {
	/**判断是否需要屏蔽广告
	 * @param fillingDataVO
	 * @param req
	 * @param vo
	 * @param rsp
	 * @return
	 */
	public boolean isNeedShield(FillingDataVO fillingDataVO ,SdkRequestVO req,SdkResponseVO rsp)throws Exception;

}
