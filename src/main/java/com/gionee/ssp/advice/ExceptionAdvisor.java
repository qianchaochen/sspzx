package com.gionee.ssp.advice;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gionee.ssp.service.sdk.rsp.SdkRspService;
import com.wk.exception.Errors;
import com.wk.ssp.mvc.exception.BusinessException;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.utils.local.LocalInfo;
import com.wk.ssp.utils.local.ThreadLocalManager;
import com.wk.ssp.utils.log.LogInfo;
import com.wk.ssp.utils.log.LogLevel;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月6日
 */
@Component
@Aspect
public class ExceptionAdvisor {

	Logger logger = LogManager.getLogger(getClass());
    /**
     * sdk返回信息服务层
     */
    @Autowired
    SdkRspService sdkRspService;

	@AfterThrowing(value = "execution(public * com.gionee.ssp.controller.*.*(..)) throws Exception", throwing = "ex")
	public void afterThrowingAdvice(final JoinPoint joinPoint, final Exception ex) {

		String errorType = "";
		//打印异常信息控制
    	ex.printStackTrace();
    	logger.error(ex.getMessage(),ex);

		if (ex.getClass().equals(BusinessException.class)) {
			errorType = "businessError";
		} else {
			errorType = "SystemError";
		}

		ServletRequestAttributes respAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();

		LogInfo logInfo = WKLogManager.getLOG();
		
		SdkResponseVO rsp = new SdkResponseVO();

		if ("businessError".equals(errorType)) {
			//业务错误
			int code = Integer.valueOf(ex.getMessage());
			rsp.setError_code(code);
			logInfo.addErrorLog(ex.getMessage());
		} else {
			//系统错误
			rsp.setError_code(Errors.INTERNAL_ERROR);
			logInfo.addErrorLog("System error");
			logInfo.addrequestId(ThreadLocalManager.getLocal().getRequest_id(), LogLevel.SYSERROR);
			logInfo.addSysErrorLog(ex);
			
		}

		//设置req_id
		LocalInfo localInfo = ThreadLocalManager.getLocal();
		rsp.setRequest_id(localInfo.getRequest_id());
		
		//拼装返回信息
		sdkRspService.setAdRspInfo(rsp);
		
		//异常返回
		this.printRsp(respAttributes, rsp);
	
	}
	
	/**异常返回处理
	 * @param respAttributes
	 * @param vo
	 */
	private void printRsp(ServletRequestAttributes respAttributes,SdkResponseVO vo){
		HttpServletResponse rsp = respAttributes.getResponse();
		
		OutputStream outputStream = null;
		
		HttpServletRequest req = respAttributes.getRequest();
		try {
            rsp.setHeader("Content-Type", "application/json");
			outputStream = rsp.getOutputStream();
			String response = JsonUtils.writeObject2Json(vo);
			
			String callback_fun=req.getParameter("callback"); //如果是jsonp请求,拼接jsonp返回字符串 add by dingyw
			if(!StringUtils.isEmpty(req.getParameter("callback"))){
				outputStream.write((callback_fun+"("+response+")").getBytes());
			}else{
				outputStream.write(response.getBytes());
			}
		} catch (Exception e) {
			} finally {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
				}
			}
		}
}
