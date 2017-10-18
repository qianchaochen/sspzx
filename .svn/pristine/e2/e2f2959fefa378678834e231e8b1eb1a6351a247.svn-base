package com.gionee.ssp.service.wk.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.wk.ValidateService;
import com.wk.exception.SDKBusinessError;
import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.utils.ValidateUtils;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.sdk.SDKInfoVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**校验服务
 * @author dingyw
 *
 * 2017年10月12日
 */
@Service
public class ValidateServiceImpl implements ValidateService {

	@Override
	public void validateVo(SdkRequestVO req) {
		ValidateUtils.SDKValidate(req);
	}

	@Override
	public void validateParam(HttpServletRequest req) {	
		SDKInfoVO vo = new SDKInfoVO();
		try {
			BeanUtils.populate(vo, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 

		if (StringUtils.isBlank(vo.getSvr())) {

			throw new BusinessException(SDKBusinessError.MISSING_SVR);
		}
		if (null == vo.getDevice()) {

			throw new BusinessException(SDKBusinessError.MISSING_DEVICE);
		}
		if (StringUtils.isBlank(vo.getCuid())) {

			throw new BusinessException(SDKBusinessError.MISSION_CUID);
		}
		if (null == vo.getClient_id()) {

			throw new BusinessException(SDKBusinessError.MISSING_CLIENT_ID);
		}
		if (null == vo.getDevice_id()) {

			throw new BusinessException(SDKBusinessError.MISSING_DEVICE_ID);
		}

		WKLogManager.getLOG().addReqAdLog("svr", vo.getSvr()).addReqAdLog("device_n", vo.getDevice()).addReqAdLog("cuid", vo.getCuid())
				.addReqAdLog("client_id", vo.getClient_id()).addReqAdLog("device_id", vo.getDevice_id());
	}
	@Override
	public void validatePreloadParam(HttpServletRequest reqs) {
		
		String channel = reqs.getParameter("channel");
		String time = reqs.getParameter("time");
		
		if (StringUtils.isBlank(channel)) {
			throw new BusinessException(SDKBusinessError.PARAM_ERROR);
		}else if(!"STORY_LOCKER".equals(channel)){
			throw new BusinessException(SDKBusinessError.VERIFICATION_FAILURE);
		}
		
		if (StringUtils.isBlank(time)) {
			
			throw new BusinessException(SDKBusinessError.PARAM_ERROR);
		}
	}
}
