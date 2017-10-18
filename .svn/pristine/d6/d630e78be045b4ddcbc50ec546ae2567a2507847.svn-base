package com.gionee.ssp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gionee.ssp.action.SDKGetAdAction;
import com.gionee.ssp.service.wk.ValidateService;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @description:SDK管理器
 */
@RestController
public class SDKController extends BaseSDKController {

	/**
	 * 校验服务层
	 */
	@Autowired
	ValidateService validateService;
	/**
	 * 获取广告action层
	 */
	@Autowired
	SDKGetAdAction sdkGetAdAction;

	@RequestMapping(value = "/v1.1/getad")
	public ResponseEntity<SdkResponseVO> getAd(final HttpServletRequest req, final HttpServletResponse response)
			throws Exception {

		WKLogManager.getLOG().addReqAdLog("from", "sdk");

		// IP防刷策略
		this.ip_anti(req);
		
		// 公共参数校验
		validateService.validateParam(req);
		
		//获取请求json
		String json = this.getReqJson(req);

		//获取广告
		SdkResponseVO rsp = sdkGetAdAction.getAd(json);

		return ResponseEntity.ok(rsp);
	}
}
