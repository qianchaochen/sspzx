package com.gionee.ssp.controller.log;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gionee.ssp.vo.log.ShieldAdLogVo;
import com.wk.ssp.utils.JsonUtils;

/**
 * @description: sdk上报被屏蔽广告接口
 */
@RestController
public class SDKAdShieldLogController {
	
	private Logger logger = LogManager.getLogger();
	private Marker SdkShieldAdLogMarker = MarkerManager.getMarker("SdkShieldAdLog"); 
	
    @RequestMapping("/shield/report")
	public void shield(final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
    	
    	//返回后记录日志即可
		OutputStream outputStream = response.getOutputStream();
		outputStream.flush();
		outputStream.close();
		
		ShieldAdLogVo vo = new ShieldAdLogVo();
		BeanUtils.populate(vo, request.getParameterMap());
		
		//记录日志
		logger.info(SdkShieldAdLogMarker, JsonUtils.writeObject2Json(vo));

	}
}
