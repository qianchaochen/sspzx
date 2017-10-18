package com.gionee.ssp.service.debug;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
public interface DebugCommonGenService {
	
	/**
	 * @param fillingDataVO
	 * @param vo
	 * @param req
	 */
	public void genCommonAd(FillingDataVO fillingDataVO,SdkResponseAdVO vo,SdkRequestVO req);

}
