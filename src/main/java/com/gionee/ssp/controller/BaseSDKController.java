package com.gionee.ssp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gionee.ssp.service.wk.AntiCheatingService;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.utils.http.WKHttpUtils;
import com.wk.ssp.utils.log.WKLogManager;


/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public class BaseSDKController {

	/**
	 * 反作弊服务层
	 */
	@Autowired
	AntiCheatingService antiCheatingService;
	
	
	/**
	 *是否开启反作弊功能
	 */
	@Value("#{anti_cheating_config.IS_ON}")
	private String anti_is_on;

	/**IP反作弊
	 * @param request
	 * @throws Exception
	 */
	public void ip_anti(HttpServletRequest request) throws Exception {
		
		if (Constant.ANTI_ON.equals(anti_is_on)) {
			// 反刷策略，每秒钟请求次数超过上限，将访问IP拉入黑名单
			String ip = WKHttpUtils.getIpAddr(request);
			antiCheatingService.validate_InBlackList(ip);
			if (!antiCheatingService.validate_access(ip)) {
				antiCheatingService.addBlackList(ip);
				throw new BusinessException(Errors.IN_BLACKLIST);
			}
		}

	}

	/**获取请求的json
	 * @param request
	 * @return
	 */
	public String getReqJson(HttpServletRequest request) {

		String reqJson = request.getParameter("reqjson");
		
		WKLogManager.getLOG().addReqAdLog("reqjson", reqJson);

		return reqJson;
	}

}
