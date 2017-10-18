package com.gionee.ssp.service.sdk.rsp.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.ad.AdInfoService;
import com.gionee.ssp.service.tracker.InsertTrackerService;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
public class BaseSdkTrackerServiceImpl {
	/**
	 *插入监播服务层 
	 */
	@Autowired
	InsertTrackerService insertTrackerService;
	
	/**
	 * 广告位服务层
	 */
	@Autowired
	AdInfoService adInfoService;
	
	
	/**
	 * 测试包名
	 */
	protected String DEMO_PACKAGE_NAME="com.gionee.ad.demo";
	
	  /**设置下载数组日志
     * @param adVO
     * @param isDown
     * @param impIds
     * @param imp_id
     */
	protected StringBuilder getIsDownBuffer(SdkResponseAdVO adVO,StringBuilder isDown){
        // 下载类广告添加下载监播，安装监播，激活监播		
        if (CommonConstant.SDKInteractionType.DOWNLOAD.getValue() == adVO.getInteraction_type()) {
        	if(null == isDown){
        		isDown = new StringBuilder("1");
        	} else {
        		isDown.append(",1");
        	}
            
        } else {
        	if(null == isDown){
        		isDown = new StringBuilder("0");
        	} else {
        		isDown.append(",0");
        	}
        }
        return isDown;

    }
    /**设置曝光的数组日志
     * @param adVO
     * @param isDown
     * @param impIds
     * @param imp_id
     */
    protected StringBuilder getImpIdBuffer(StringBuilder impIds,String imp_id){        
        
        if(null == impIds){
        	impIds = new StringBuilder("1");
        } else {
        	impIds.append("," + imp_id);
        }
        return impIds;
    }
    
    /**添加日志
     * @param impIds
     * @param isDown
     */
    protected void addLog(StringBuilder impIds,StringBuilder isDown,boolean isTest) {
		 
		if(isTest){
			 impIds = null;
			 isDown = null;
		}
		if(null != impIds){
			WKLogManager.getLOG().addReqAdLog("imp_ids", impIds.toString());
		} else {
			WKLogManager.getLOG().addReqAdLog("imp_ids", "");
		}
		
		if(null != isDown){
			WKLogManager.getLOG().addReqAdLog("is_download", isDown.toString());
		} else {
			WKLogManager.getLOG().addReqAdLog("is_download", "");
		}
	}
    
    protected boolean is_push(FillingDataVO fillingDataVO){
    	//判断是否是直投广告
    	if(CommonConstant.IS_PUSH.TRUE.getValue() == fillingDataVO.getIs_ipush()){
    		return true;
    	}
    	return false;
    }

}
