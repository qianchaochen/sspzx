package com.gionee.ssp.service.req.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.req.ConvertReqAdSlotService;
import com.gionee.ssp.service.req.adSlot.banner.ConvertReqBannerService;
import com.gionee.ssp.service.req.adSlot.nativ.ConvertReqNativeService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.utils.StringUtils;
import com.wk.ssp.vo.FillNativeVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkAdslotVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqAdSlotServiceImpl implements ConvertReqAdSlotService{
	
	/**
	 * 原生广告服务层
	 */
	@Autowired
	ConvertReqNativeService convertReqNativeService;
	
	/**
	 * 横幅服务层
	 */
	@Autowired
	ConvertReqBannerService convertReqBannerService;
	
	/**
	 * @title: setAdSlot
	 * @description: 转换广告位字段
	 * @param reqBuilder
	 *            百度Request字段
	 * @param req
	 *            SDK请求字段
	 * @throws Exception
	 */
	public void setAdSlot(Builder reqBuilder, SdkRequestVO req, FillingDataVO fillingDataVO)
			throws Exception {

		SdkAdslotVO adslot = req.getAdslot();
		WKSSP.AdSlot.Builder slotBuilder = WKSSP.AdSlot.newBuilder();
		slotBuilder.setId(adslot.getAdslot_id());
		slotBuilder.setBidfloor(fillingDataVO.getBidfloor());
		// 底价货币类型非必填 默认为“CNY”
		if (StringUtils.isNotBlank(fillingDataVO.getBidfloorcur())) {

			slotBuilder.setBidfloorcur(fillingDataVO.getBidfloorcur());
		}

		List<FillNativeVO> fillNativeVOs = fillingDataVO.getNativ();
		if (!ObjectUtils.isEmpty(fillNativeVOs)) {
			convertReqNativeService.setNative(slotBuilder, fillNativeVOs);
		} else {

			convertReqBannerService.setBanner(slotBuilder, req, fillingDataVO);
		}
		slotBuilder.setInstl(fillingDataVO.getInstl());
		slotBuilder.setIsSplashScreen(fillingDataVO.getIs_splash());
		slotBuilder.setCount(fillingDataVO.getAd_cnt());
		List<Integer> bitcs = fillingDataVO.getBitc();
		if (!ObjectUtils.isEmpty(bitcs)) {
			for (int bitc : bitcs) {
				slotBuilder.addBitc(bitc);
			}
		}

		reqBuilder.setAdSlot(slotBuilder);
	}


}
