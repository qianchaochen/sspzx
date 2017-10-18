package com.gionee.ssp.service.wk.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gionee.ssp.service.wk.SdkInfoService;
import com.wk.ssp.vo.sdk.SDKInfoVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 *         2017年4月19日
 */
@Service
public class SdkInfoServiceImpl implements SdkInfoService {
	
	// 填充sdk数据信息
	public void setSDKInfo(SdkRequestVO vo) {

		ServletRequestAttributes respAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();

		HttpServletRequest req = respAttributes.getRequest();
		
		SDKInfoVO sdkInfoVO = new SDKInfoVO();
		try {
			BeanUtils.populate(sdkInfoVO, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (StringUtils.isEmpty(sdkInfoVO.getApilevel())) {
			sdkInfoVO.setApilevel("");
		} 

		vo.setSdkInfoVO(sdkInfoVO);
	}
	
}
