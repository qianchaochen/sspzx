package com.gionee.ssp.service.ip.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.cp.CheckCpService;
import com.gionee.ssp.service.ip.ClientIpService;
import com.wk.ssp.utils.log.LogInfo;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年10月12日
 */
@Service
public class ClientIpServiceImpl implements ClientIpService{

	/**
	 * 校验cp服务层 
	 */
	@Autowired
	CheckCpService checkCpService;

	@Override
	public void setIp(SdkRequestVO req){
		if (this.needRealIp(req)) { // SDK请求客户端
			LogInfo logInfo = WKLogManager.getLOG();
			String realIp = logInfo.getIp(); // logInfo在之前已经获取了客户端的IP
			req.getNetwork().setIp(realIp);

		} else { // 锁屏请求过来的
			LogInfo logInfo = WKLogManager.getLOG();
			logInfo.setIp(req.getNetwork().getIp()); // 锁屏请求过来的，取报文体的IP作为客户端真实的IP
		}

	}

	private boolean needRealIp(SdkRequestVO req) {
		if (checkCpService.isLockOld(req.getApp().getApp_id(), req.getAdslot().getAdslot_id())
				|| checkCpService.isMusic(req.getApp().getApp_id(), req.getAdslot().getAdslot_id())) {
			return false;
		}
		return true;
	}

}
