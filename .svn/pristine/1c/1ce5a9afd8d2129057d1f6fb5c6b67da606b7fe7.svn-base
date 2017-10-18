package com.gionee.ssp.service.rsp.tracker.impl;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertRspTrackerService;
import com.wk.model.adx.WKSSP;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertRspTrackerServiceImpl extends BaseConvertRspTrackerServiceImpl implements ConvertRspTrackerService{
	
	@Override
	public void convertTrackersInfo(SdkResponseAdVO rsp,WKSSP.Ad ad){
		// 填充上游系统展示监播
		convertImpTrackerService.convertTrackerInfo(rsp, ad);

		// 填充上游系统点击监播
		convertClkTrackerService.convertTrackerInfo(rsp, ad);

		// 填充上游系统下载监播
		convertDownLoadTrackerService.convertTrackerInfo(rsp, ad);
		
		// 填充上游系统安装监播
		convertInstallTrackerService.convertTrackerInfo(rsp, ad);
		
		// 填充上游系统激活监播
		convertActiveTrackerService.convertTrackerInfo(rsp, ad);
		
		// 填充开始下载监播
		convertDownLoadStartTrackerService.convertTrackerInfo(rsp, ad);

		// 106 增加了4g下载开始和下载完成，wifi预约下载开始和下载完成
		// 4g下载开始
		convertDownLoadStart_4G_TrackerService.convertTrackerInfo(rsp, ad);

		// 4g下载完成
		convertDownLoadFinish_4G_TrackerService.convertTrackerInfo(rsp, ad);


		// wifi预约下载开始监播
		convertDownLoadStart_wifi_TrackerService.convertTrackerInfo(rsp, ad);


		// wifi预约下载完成监播
		convertDownLoadFinish_wifi_TrackerService.convertTrackerInfo(rsp, ad);

		// app启动监播
		convertLaunchTrackerService.convertTrackerInfo(rsp, ad);

	}

}
