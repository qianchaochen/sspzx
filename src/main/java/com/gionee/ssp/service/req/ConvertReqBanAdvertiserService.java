package com.gionee.ssp.service.req;

import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ConvertReqBanAdvertiserService {
	
	/**转换禁用广告主
	 * @param reqBuilder
	 * @param vo
	 */
	public void setBanAdvertiser(Builder reqBuilder,FillingDataVO vo);

}
