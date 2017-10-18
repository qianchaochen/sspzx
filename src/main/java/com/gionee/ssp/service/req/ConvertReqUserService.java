package com.gionee.ssp.service.req;

import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.FillUserVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
public interface ConvertReqUserService {
	
	/**转换用户信息
	 * @param reqBuilder
	 * @param userVO
	 */
	public void setUser(Builder reqBuilder, FillUserVO userVO);

}
