package com.gionee.ssp.service.ad.version.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.ad.version.SdkVersion_177_Service;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.SdkVersionUtil;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class SdkVersion_177_ServiceImpl implements SdkVersion_177_Service{
	
	@Override
	public void setInteractInfo(FillingDataVO fillingDataVO, SdkRequestVO req,SdkResponseAdVO vo){
		// 如果是sdk1.7.7版本，则以h5地址为准，
		if (SdkVersionUtil.compareVersion(req.getSdkInfoVO().getSvr(), Constant.SDK_VERSION_1_7_7) == 0) {
			if (StringUtils.isNotEmpty(vo.getH5_url())) {
				vo.setClkurl(vo.getH5_url());
			}
		}

		// 1.7.7 及以下版本，将interact_type固定为1
		if (SdkVersionUtil.compareVersion(req.getSdkInfoVO().getSvr(), Constant.SDK_VERSION_1_7_7) <= 0) {
			vo.setInteraction_type(CommonConstant.SDKInteractionType.WEB.getValue());
		}

		// 1.7.7以上版本，当交互类型为应用下载时，将interact_type重新设置成jump_type设置的值
		this.dealCurrentVersion(fillingDataVO, req, vo);
		
	}
	/**1.7.7以上版本，当交互类型为应用下载时，将interact_type重新设置成jump_type设置的值
	 * @param adCofig
	 * @param req
	 * @param vo
	 */
	private void dealCurrentVersion(FillingDataVO adCofig, SdkRequestVO req,SdkResponseAdVO vo){
		
		if (SdkVersionUtil.compareVersion(req.getSdkInfoVO().getSvr(), Constant.SDK_VERSION_1_7_7) > 0) {
			//app下载
			if (vo.getInteraction_type() == CommonConstant.AdInteractionType.DOWNLOAD.getValue()) {
				
				//1：h5 落地页  2：直接下载，5：下载卡片，6：跳转游戏大厅、应用商店
				vo.setInteraction_type(adCofig.getJump_type());

				if (adCofig.getJump_type() == CommonConstant.SDKInteractionType.JUMP_APPSTORE_GAMEHALL.getValue()) {
					vo.setDeep_link(vo.getClkurl());
				} else if (adCofig.getJump_type() == CommonConstant.SDKInteractionType.WEB.getValue()) {
					if (StringUtils.isNotEmpty(vo.getH5_url())) {
						vo.setClkurl(vo.getH5_url());
					}
				} 
			}
			//app唤醒
			if (vo.getInteraction_type() == CommonConstant.SDKInteractionType.APP_WAKE.getValue()) {
				if (StringUtils.isNotEmpty(vo.getClkurl())) {
					vo.setH5_url(vo.getClkurl());
				}
			}
		}
	}

}
