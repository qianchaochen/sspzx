package com.gionee.ssp.service.rsp;

import com.wk.model.adx.WKSSP;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ConvertRspAdService {
	
	/**转换广告服务层
	 * @param adxAd
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public SdkResponseAdVO covertAd(WKSSP.Ad adxAd, FillingDataVO vo) throws Exception;

}
