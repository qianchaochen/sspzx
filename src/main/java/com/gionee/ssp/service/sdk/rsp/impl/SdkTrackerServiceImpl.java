package com.gionee.ssp.service.sdk.rsp.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.sdk.rsp.SdkTrackerService;
import com.wk.ssp.utils.local.ThreadLocalManager;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月11日
 */
@Service
public class SdkTrackerServiceImpl extends BaseSdkTrackerServiceImpl implements SdkTrackerService{

    @Override
    public void insertTrackers(SdkResponseVO rsp, SdkRequestVO req, FillingDataVO fillingDataVO) throws Exception {
    	
    	// 获取请求广告位类型
		// @wuxing @2017.08.09 发现SDK的广告类型和在线服务以及PHP的定义不一样, 插屏和开屏反了
		// 这里是以前玩咖用这个操作转过来的, 在返回的时候再次设置回去
    	int ad_type = adInfoService.getAdType(fillingDataVO);

    	List<SdkResponseAdVO> adms = rsp.getAdms();
    	String requestId = ThreadLocalManager.getLocal().getRequest_id();
        rsp.setRequest_id(requestId); // 填充requestID
        
		int impId = 1;
		StringBuilder impIds = null;
		StringBuilder isDown = null;
		
		boolean isTest =false;
		//遍历所有广告
		for(SdkResponseAdVO adVO : adms){
			
			String imp_id = String.valueOf(impId);
			//根据广告位再重新设置ad_type
			adVO.setAd_type(ad_type);
			 // 直投广告展示监播和点击监播已在请求后添加，此处无需再次添加
	        if (false==this.is_push(fillingDataVO)) {
	        	// 添加展示监播
	        	insertTrackerService.insertImp(req, adVO, requestId, imp_id); 
	        	// 添加点击监播
	        	insertTrackerService.insertClick(req, adVO, requestId, imp_id); 
	        }
	        //修改isDown日志数组
	        isDown= this.getIsDownBuffer(adVO, isDown);
	        //修改曝光ID数组
	        impIds = this.getImpIdBuffer(impIds, imp_id);
	        
	        //自加
	        impId++;
	        
	        //判断是否测试包名
	        isTest = this.DEMO_PACKAGE_NAME.equals(adVO.getBundle());
	        if(true==isTest){
	        	continue;
	        }

        	//插入下载监播
        	insertTrackerService.insertDownload(req, adVO, requestId, imp_id);
        	//插入安装监播
        	insertTrackerService.insertInstall(req, adVO, requestId, imp_id);
        	//查询激活监播
        	insertTrackerService.insertActive(req, adVO, requestId, imp_id);

		}	
		//添加日志
		this.addLog(impIds, isDown,isTest);

    }
}
