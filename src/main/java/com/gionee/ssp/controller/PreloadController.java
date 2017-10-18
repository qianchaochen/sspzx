package com.gionee.ssp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gionee.ssp.service.preload.lock.PreloadService;
import com.gionee.ssp.service.wk.ValidateService;
import com.gionee.ssp.vo.preload.PreloadResponseVo;
import com.wk.exception.Errors;
import com.wk.exception.SDKBusinessError;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.log.WKLogManager;


/**锁屏预加载广告接口
 * 给锁屏调用
 * @author dingyw
 *
 * 2017年9月5日
 */
@RestController
public class PreloadController{
	
	/**
	 * 校验服务层
	 */
	@Autowired
	ValidateService validateService;
	/**
	 *预加载广告服务层 
	 */
	@Autowired
	PreloadService preloadService;

	/**
	 * 
	 * @Title: getImgInfo
	 * @Desc {预加载图片信息}
	 * @param request
	 * @param response
	 * @throws Exception
	 * @retrun ResponseEntity<PreloadResponseVo>
	 * @author zhengk
	 * @date Apr 6, 2017 11:16:37 AM
	 */
	@RequestMapping(value = "/preload/imgInfo")
	public ResponseEntity<PreloadResponseVo> getImgInfo(final HttpServletRequest request, final HttpServletResponse response)
			 {
		
		WKLogManager.getLOG().addReqAdLog("FROM", "STORY_LOCKER");
		PreloadResponseVo rtnVo = new PreloadResponseVo();
		rtnVo.setCode(Errors.NO_ERROR+"");
		rtnVo.setMsg("");
		
		try {
			validateService.validatePreloadParam(request);
			rtnVo = preloadService.getPreloadData();
		} catch (Exception e) {
			String code = StringUtils.isEmpty(e.getMessage())?SDKBusinessError.INTERNAL_ERROR+"":e.getMessage();
			rtnVo.setCode(code);
			rtnVo.setMsg(Errors.getErrorMessage(Integer.parseInt(code)));
		}
		
		try {
			WKLogManager.getLOG().addReqAdLog("RES_DATA", JsonUtils.writeObject2Json(rtnVo));
		} catch (JsonProcessingException e1) {
			
		}
		
		return ResponseEntity.ok(rtnVo);
	}
}
