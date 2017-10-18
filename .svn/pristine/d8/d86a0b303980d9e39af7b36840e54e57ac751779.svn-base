package com.gionee.ssp.controller.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.log.LogUtils;
import com.wk.ssp.vo.RBI.CrashVo;


/**sdk crash接口
 * @author dingyw
 *
 * 2017年10月11日
 */
@RestController
public class SDKCrashLogController extends BaseSDKCrashLogController{
	
	private Logger logger = LogManager.getLogger();
	private Marker crashLogMarker = MarkerManager.getMarker("SdkCrashLog"); 
    /**
     * 崩溃日志接口接口
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/RBI/crash")
    public void sdkCrash(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

    	//获取请求的参数
		String json = this.getJson(request);
		//获取对象
        CrashVo vo = JsonUtils.readJson2Object(json, CrashVo.class);
        //转为日志字符串
        String crash_log=this.getLog(vo);
      
        //记录日志
      	logger.info(crashLogMarker, crash_log);
    }
 
    /**拼接日志字符串
     * @param vo
     * @return
     */
    private String getLog(CrashVo vo){
	  StringBuilder sb=new StringBuilder();
      sb.append(LogUtils.cat("svr", vo.getSvr()));
      sb.append(LogUtils.cat("device", vo.getDevice()));
      sb.append(LogUtils.cat("cuid", vo.getCuid()));
      sb.append(LogUtils.cat("clientId", vo.getClient_id()));
      sb.append(LogUtils.cat("deviceId", vo.getDevice_id()));
      sb.append(LogUtils.cat("appId", vo.getApp_id()));
      sb.append(LogUtils.cat("ovr", vo.getOvr()));
      sb.append(LogUtils.cat("imei", vo.getInfo_ms()));
      sb.append(LogUtils.cat("android_id", vo.getOs_id()));
      sb.append(LogUtils.cat("netType", vo.getNet_type()));
      sb.append(LogUtils.cat("sn", vo.getSn()));
      sb.append(LogUtils.cat("osLevel", vo.getOs_level()));
      sb.append(LogUtils.cat("channelId", vo.getChannel_id()));
      sb.append(LogUtils.cat("errorInfo", vo.getError_info().replaceAll("%%", "\n\t")));
      return sb.toString();
    }
}
