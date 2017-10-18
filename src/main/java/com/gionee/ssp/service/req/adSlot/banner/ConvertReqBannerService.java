package com.gionee.ssp.service.req.adSlot.banner;

import com.wk.model.adx.WKSSP;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ConvertReqBannerService {
	
	/**转换横幅数据
	 * @param adslot
	 * @param sdkRes
	 * @param vo
	 */
	public void setBanner(WKSSP.AdSlot.Builder adslot, SdkRequestVO sdkRes, FillingDataVO vo);

}
