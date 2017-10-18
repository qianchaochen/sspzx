package com.gionee.ssp.service.debug.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gionee.ssp.service.debug.DebugService;
import com.gionee.ssp.service.debug.GetDebugAdRspService;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.log.WKLogManager;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;


/**sdk响应测试数据服务
 * @author dingyw
 *
 * 2017年9月5日
 */
@Service
public class DebugServiceImpl extends BaseDebugServiceImpl implements DebugService {
	
	
	/**
	 * 获取debug广告返回
	 */
	@Autowired
	GetDebugAdRspService getDebugAdRspService;
	
	@Override
	public SdkResponseVO getDebugAd(SdkRequestVO req, FillingDataVO fillingDataVO) {

        // 指定本次请求为测试请求
        WKLogManager.getLOG().addReqAdLog("is_debug", "1");

        // 测试ADXID
        WKLogManager.getLOG().addReqAdLog(Constant.TARGET_ADX, Constant.DEBUG_TARGET_ADX_ID);
        
		SdkResponseVO rsp = new SdkResponseVO();
		rsp.setError_code(0);
		
		//核心流程
		getDebugAdRspService.getAdRsp(rsp, req, fillingDataVO);
		
		SdkResponseAdVO vo = rsp.getAds();
		List<SdkResponseAdVO> adms = new ArrayList<>();
		//请求个数
		int cnt = fillingDataVO.getAd_cnt();
		for(int i = 0; i < cnt; i++){
			adms.add(vo);
		}
		rsp.setAdms(adms);
		rsp.setAds(null);
		rsp.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL);
		rsp.setGet_ad_in_same_view_times(Constant.VIEW_INTERVAL_TIMES); //重复请求广告次数
		// 时间戳功能暂不支持
		rsp.setExpiration_time(Constant.AD_EXPIRATION_TIME);

		return rsp;
	}

	
}
