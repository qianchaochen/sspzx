package com.gionee.ssp.service.sdk.rsp.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.sdk.rsp.SdkRspService;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
@Service
public class SdkRspServiceImpl extends BaseSdkSspServiceImpl implements SdkRspService{
	

    @Override
    public void setAdRspInfo(SdkResponseVO rsp) {
    	this.setAdRspInfo(rsp, null);
    }
    
    @Override
    public void setAdRspInfo(SdkResponseVO rsp, SdkRequestVO req) {
        
        rsp.setSwich(AUTO_UPDATE_SWICH); // 自更新开关
        rsp.setDwlconfirm(DOWN_CONFIRM_TWICE_SWICH); // 下载二次确认开关
        rsp.setLg_swh(LOGO_SWICH); // logo开关
        rsp.setBrkdwn(break_down_swich); // 上传崩溃日志开关
        
        if (!ObjectUtils.isEmpty(rsp.getAdms())) {
            
            for (SdkResponseAdVO adm : rsp.getAdms()) {       
                // 配置服务器控制字段
            	int ad_type=adm.getAd_type();
            	if(CommonConstant.SDK_AD_TYPE.BANNER.getValue() == ad_type){
                    adm.setRqto(Double.valueOf(RQTO_BANNER_TIMEOUT));
                    adm.setRdto(RDTO_BANNER_TIMEOUT);
            	}else if(CommonConstant.SDK_AD_TYPE.SPLASH_SCREEN.getValue() == ad_type){
                    adm.setRqto(Double.valueOf(RQTO_SPLASH_TIMEOUT));
                    adm.setRdto(RDTO_SPLASH_TIMEOUT);
            	}else if(CommonConstant.SDK_AD_TYPE.INSERT_SCREEN.getValue() == ad_type){
                    adm.setRqto(Double.valueOf(RQTO_INSTL_TIMEOUT));
                    adm.setRdto(RDTO_INSTL_TIMEOUT);
            	}else if(CommonConstant.SDK_AD_TYPE.NATIVE.getValue() == ad_type){
                    adm.setRqto(Double.valueOf(RQTO_NATIVE_TIMEOUT));
                    adm.setRdto(RDTO_NATIVE_TIMEOUT);
            	}else{    
                    adm.setRqto(3);//请求超时默认3秒
                    adm.setRdto(2); //渲染默认2秒
                }
                
                if (CommonConstant.SDKInteractionType.WEB.getValue() == adm.getInteraction_type()) {
                    adm.setCtop(imp_tracker_operate_time);
                } else if (CommonConstant.SDKInteractionType.DOWNLOAD.getValue() == adm.getInteraction_type() && 
                		CommonConstant.TRACKER_TIMING_TYPE.PAGE_LOAD_START.getValue() == imp_tracker_operate_time) {
                    adm.setCtop(CommonConstant.TRACKER_TIMING_TYPE.CLICK.getValue());
                } else if (CommonConstant.SDKInteractionType.DOWNLOAD.getValue() == adm.getInteraction_type() && 
                		CommonConstant.TRACKER_TIMING_TYPE.PAGE_LOAD_FINISH.getValue() == imp_tracker_operate_time) {
                    adm.setCtop(CommonConstant.TRACKER_TIMING_TYPE.DOWNLOAD_START.getValue());
                } else {
                    adm.setCtop(CommonConstant.TRACKER_TIMING_TYPE.CLICK.getValue());
                }
            }
        }
        
        if (req != null) { 
            // 浮层广告暂不开启logo显示
            if ("7".equals(req.getApp().getApp_id())) {
                rsp.setLg_swh(0);
            }
        }
    }


}
