package com.gionee.ssp.service.rsp.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.rsp.ConvertRspAdService;
import com.gionee.ssp.service.rsp.ConvertRspService;
import com.wk.exception.Errors;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.WKSSPResponse;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertRspServiceImpl implements ConvertRspService{
	
	/**
	 * 转换广告服务层 
	 */
	@Autowired
	ConvertRspAdService convertRspAdService;
	
	/**
	 * @title: getSDKReq
	 * @description: 将ADX响应转换成SDK响应
	 * @param response
	 *            ADX响应对象
	 * @param adSlotType
	 *            广告位类型
	 * @return
	 */
	public SdkResponseVO getSdkRsp(WKSSPResponse response, FillingDataVO vo) throws Exception {

		SdkResponseVO rsp = new SdkResponseVO();
		int error_code = response.getCode();
		
		if (0 == error_code) {
			rsp.setError_code(error_code);
			this.setAds(rsp, response, vo);
			//添加再次请求的时间间隔
			rsp.setGet_ad_in_same_view_interval(Constant.VIEW_INTERVAL);
			rsp.setGet_ad_in_same_view_times(Constant.VIEW_INTERVAL_TIMES);
		} else {
			//创建无内容返回
			rsp.setError_code(Errors.NO_CONTENT);
			
			//添加空返回list，防止记录返回广告个数是出现空指针
			rsp.setAdms(new ArrayList<>());
		}
		// 时间戳功能暂不支持
		rsp.setExpiration_time(Constant.AD_EXPIRATION_TIME);

		return rsp;
	}
	

	/**
	 * @title: setAd
	 * @description: 转换响应广告信息
	 * @param rsp
	 *            sdk响应信息
	 * @param wk_rsp
	 *            adx响应信息
	 */
	private void setAds(SdkResponseVO rsp, WKSSPResponse wk_rsp, FillingDataVO vo) throws Exception {

		List<SdkResponseAdVO> result = new ArrayList<>();
		
		List<WKSSP.Ad> list = wk_rsp.getAdsList();
		
		if(!ObjectUtils.isEmpty(list)){
			
			for(WKSSP.Ad adxAd : list){
				result.add(convertRspAdService.covertAd(adxAd, vo));
			}
		}
		rsp.setAdms(result);
	}

}
