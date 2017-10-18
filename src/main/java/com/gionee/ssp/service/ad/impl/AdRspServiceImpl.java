package com.gionee.ssp.service.ad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.ad.AdRspService;
import com.gionee.ssp.service.ad.version.SdkVersion_177_Service;
import com.gionee.ssp.service.adx.impl.BaseAdxServiceImpl;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.AdTimeVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * 广告返回设置信息服务层
 * 
 * @author dingyw
 *
 *         2017年9月6日
 */
@Service
public class AdRspServiceImpl extends BaseAdxServiceImpl implements AdRspService {

	/**
	 * 1.7.7 SDK特殊处理业务逻辑
	 */
	@Autowired
	SdkVersion_177_Service SdkVersion_177_Service;

	@Override
	public void setAdRsp(FillingDataVO adCofig, SdkRequestVO req, SdkResponseVO rsp) {
		if (rsp == null) {
			return;
		}	
		//设置是否静默安装
		this.setIsSilentInstall(req, rsp);
		
		List<SdkResponseAdVO> list = rsp.getAdms();
		//遍历
		for (SdkResponseAdVO vo : list) {
			// 判断过期时间, 过期了则不返回
			if (vo.getAd_expire() != 0 && System.currentTimeMillis() / 1000 > vo.getAd_expire()) {
				list.remove(vo);
				continue;
			}
			// 开屏广告设置默认的曝光时间等参数
			if (CommonConstant.AD_TYPE.SPLASH_SCREEN.getValue() == vo.getAd_type() && vo.getAd_time() == null) {
				AdTimeVO ad_time = new AdTimeVO();
				ad_time.setForce(force_time);
				ad_time.setSkip(skip_time);
				ad_time.setDelay(delay_time);
				ad_time.setExposure(exposure_time);
				vo.setAd_time(ad_time);
			}
			
			//设置SDK 1.7.7的交互业务逻辑
			SdkVersion_177_Service.setInteractInfo(adCofig, req, vo);
		}
	}

	/**设置是否静默安装
	 * @param req
	 * @param rsp
	 */
	private void setIsSilentInstall(SdkRequestVO req, SdkResponseVO rsp){
		// 增加静默安装开关,1:默认开, 0:关
		if (silent_install_apps.contains("no" + req.getApp().getApp_id() + ",")) {
			rsp.setSilent_install(CommonConstant.is_silent_install.FALSE.getValue());
		} else {
			rsp.setSilent_install(CommonConstant.is_silent_install.TRUE.getValue());
		}
	}

}
