package com.gionee.ssp.service.debug;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
public interface DebugImageService {
	/**
	 * @param adVO
	 * @param data
	 * @param requestVO
	 */
	public void setDebugImgUrl(SdkResponseAdVO adVO, FillingDataVO data, SdkRequestVO requestVO);

}
