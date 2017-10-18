package com.gionee.ssp.service.wk;

import javax.servlet.http.HttpServletRequest;

import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @description: 数据校验服务
 */
/**
 * @author WK86
 *
 */
public interface ValidateService {

	/**
	 * @title: validateVo
	 * @description: 校验广告请求数据
	 * @param reqdata
	 */
	public void validateVo(SdkRequestVO reqdata);
	
	/**
	 * @title: validateVo
	 * @description: 校验公参数据
	 * @param uri
	 */
	public void validateParam(HttpServletRequest request);

	/**
	 * @Title: validatePreloadParam
	 * @Desc {校验锁屏预加载请求参数}
	 * @param reqs
	 * @retrun void
	 * @author zhengk
	 * @date Apr 18, 2017 1:55:03 PM
	 */
	void validatePreloadParam(HttpServletRequest reqs);
}
