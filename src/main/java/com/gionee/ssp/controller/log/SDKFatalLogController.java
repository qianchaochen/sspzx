package com.gionee.ssp.controller.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.utils.log.LogUtils;
import com.wk.ssp.vo.RBI.FatalVo;
import com.wk.ssp.vo.RBI.RBIRspVo;


/**sdk打点接口
 * @author dingyw
 *
 * 2017年10月11日
 */
@RestController
public class SDKFatalLogController {
	private Logger logger = LogManager.getLogger();
	private Marker sdkFatalLogMarker = MarkerManager.getMarker("SdkFatalLog");
	
	/**SDK打点关闭版本
	 * rbi的配置信息
	 */
	@Value("#{rbi_config.CLOSE_VERSION}")
	private String rbi_config;

    @RequestMapping("/RBI/error")
	public ResponseEntity<RBIRspVo> RBIFatal(final HttpServletRequest request,
			final HttpServletResponse response)  {

    	//获取参数
    	FatalVo vo = new FatalVo();
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		RBIRspVo rsp = new RBIRspVo();

		String fatal_log=null;

		if (StringUtils.isNotBlank(vo.getSvr())) {			
			//记录公参
			fatal_log =this.getLog(vo);
			rsp.setSwich(this.getSwich(vo));
		} else {
			fatal_log = LogUtils.cat("svr", "param is missing");
			rsp.setSwich(Constant.RBI_FATAL_OFF);
		}
	
        //记录日志
      	logger.info(sdkFatalLogMarker, fatal_log);
		return ResponseEntity.ok(rsp);
	}
    
    /**拼接日志字符串
     * @param vo
     * @return
     */
    private String getLog(FatalVo vo){	
		StringBuilder sb=new StringBuilder();
		sb.append(LogUtils.cat("svr", vo.getSvr()));
		sb.append(LogUtils.cat("device", vo.getDevice()));
		sb.append(LogUtils.cat("cuid", vo.getCuid()));
		sb.append(LogUtils.cat("clientId", vo.getClient_id()));
		sb.append(LogUtils.cat("deviceId", vo.getDevice_id()));
		sb.append(LogUtils.cat("app_id", vo.getApp_id()));
		//记录打点信息
		if (StringUtils.isNotBlank(vo.getError_info())) {
			sb.append(LogUtils.cat("error_info", vo.getError_info()));
			
		}
		//如果打点版本在关闭名单中，关闭该版本打点功能
		if (rbi_config.indexOf(vo.getSvr()) > -1) {
			sb.append(LogUtils.cat("svr", "close sdk:" + vo.getSvr()));
        }
        return sb.toString();
    }
    /**获取开关信息
     * @param vo
     * @return
     */
    private String getSwich(FatalVo vo){
    	
    	//记录打点信息
		if (StringUtils.isNotBlank(vo.getError_info())) {

			return Constant.RBI_FATAL_ON;
		}
		//如果打点版本在关闭名单中，关闭该版本打点功能
		if (rbi_config.indexOf(vo.getSvr()) > -1) {

			return Constant.RBI_FATAL_OFF;
        }
		return Constant.RBI_FATAL_OFF;
    }
}
