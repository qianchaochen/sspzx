package com.gionee.ssp.service.req.adSlot.banner.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.service.adapter.BannerAdapter;
import com.gionee.ssp.service.adapter.InstlAdapter;
import com.gionee.ssp.service.adapter.SplashAdapter;
import com.gionee.ssp.service.req.adSlot.banner.ConvertReqBannerService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.WKSSP.AdPosition;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.DataUtils;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqBannerServiceImpl implements ConvertReqBannerService{
	
	/**
	 * 开屏adapter
	 */
	@Autowired
	SplashAdapter splashAdapter;
	
	/**
	 *插屏adapter 
	 */
	@Autowired
	InstlAdapter instlAdapter;
	
	/**
	 * 横幅adapter
	 */
	@Autowired
	BannerAdapter bannerAdapter;
	
	/**
	 * @title: setBanner
	 * @description: 转换横幅数据
	 * @param adslot
	 * @param sdkRes
	 * @param vo
	 */
	public void setBanner(WKSSP.AdSlot.Builder adslot, SdkRequestVO sdkRes, FillingDataVO vo) {

		int deviceW = sdkRes.getDevice().getW(); // 设备屏幕宽
		int deviceH = sdkRes.getDevice().getH(); // 设备屏幕高
		int instl = vo.getInstl(); // 是否是插屏的标志位
		int isSplash = vo.getIs_splash(); // 是否是开屏的标志位
		Map<String, Integer> adslotWH = null; // 适配后的广告位宽高
		WKSSP.Banner.Builder bannerBuilder = WKSSP.Banner.newBuilder();

		// 开屏广告位
		if (0 != isSplash) {
			adslotWH = DataUtils.DataAdapter(splashAdapter.getAdapter(), deviceW, deviceH);

		} else if (1 == instl) {
			// 插屏广告位
			adslotWH = DataUtils.DataAdapter(instlAdapter.getAdapter(), deviceW, deviceH);

		} else {
			// 横幅广告位
			adslotWH = DataUtils.DataAdapter(bannerAdapter.getAdapter(), deviceW, deviceH);
			bannerBuilder.setPos(AdPosition.valueOf(vo.getBanner().getPos()));
			// 添加横幅广告类型请求条件
			List<Integer> types = vo.getBanner().getType();
			// 兼容1.0版本
			if (!ObjectUtils.isEmpty(types)) {

				for (int type : types) {
					if (0 == type) {
						bannerBuilder.addCType(com.wk.model.adx.WKSSP.CreativeType.IMAGE);
					} else {
						bannerBuilder.addCType(com.wk.model.adx.WKSSP.CreativeType.TEXT_ICON);
					}
				}
			} else {
				bannerBuilder.addCType(com.wk.model.adx.WKSSP.CreativeType.IMAGE);
			}
		}

		bannerBuilder.setW(adslotWH.get(Constant.ADSLOT_WIDTH));
		bannerBuilder.setH(adslotWH.get(Constant.ADSLOT_HIGH));

		adslot.setBanner(bannerBuilder);

	}

}
