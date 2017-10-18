package com.gionee.ssp.service.req.adSlot.nativ.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.req.adSlot.nativ.ConvertReqNativeService;
import com.wk.model.adx.WKSSP;
import com.wk.model.adx.nativevo.request.NativeRequestVO;
import com.wk.model.adx.nativevo.request.ReqAssetsVO;
import com.wk.model.adx.nativevo.request.ReqImgVO;
import com.wk.model.adx.nativevo.request.ReqTitleVO;
import com.wk.ssp.mvc.Constant;
import com.wk.ssp.utils.JsonUtils;
import com.wk.ssp.vo.FillNativeVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqNativeServiceImpl implements ConvertReqNativeService{

	/**
	 * @title: setNative
	 * @description: 转化原生广告请求
	 * @param iNative
	 * @param fillingDataVO
	 * @throws Exception
	 */
	public void setNative(WKSSP.AdSlot.Builder slotBuilder, List<FillNativeVO> fillNativeVOs) throws Exception {

		WKSSP.Native.Builder iNative = WKSSP.Native.newBuilder();
		NativeRequestVO nativeVO = new NativeRequestVO();

		nativeVO.setVer(Constant.NATIVE_VER);
		this.setReqAssets(nativeVO, fillNativeVOs);

		String nativeJson = JsonUtils.writeObject2Json(nativeVO);
		iNative.setVer(Constant.NATIVE_VER);
		iNative.setRequest(nativeJson);

		slotBuilder.setNative(iNative);

	}
	
	/**
	 * @title: setReqAssets
	 * @description: 转换原生广告请求限制条件
	 * @param nativeVO
	 * @param fillNativeVO
	 */
	private void setReqAssets(NativeRequestVO nativeVO, List<FillNativeVO> list) {

		List<ReqAssetsVO> reqAssetsVOs = new ArrayList<ReqAssetsVO>();

		for (FillNativeVO fillNativeVO : list) {

			ReqAssetsVO reqAssetsVO = new ReqAssetsVO();
			reqAssetsVO.setType(fillNativeVO.getType());
			this.setReqTitle(reqAssetsVO, fillNativeVO);
			this.setReqImg(reqAssetsVO, fillNativeVO);

			reqAssetsVOs.add(reqAssetsVO);
		}
		nativeVO.setReqAssets(reqAssetsVOs);
	}

	/**
	 * @title: setReqTitle
	 * @description: 转换原生广告请求主题限制
	 * @param reqAssetsVO
	 * @param vo
	 */
	private void setReqTitle(ReqAssetsVO reqAssetsVO, FillNativeVO vo) {

		ReqTitleVO title = new ReqTitleVO();
		title.setLen(vo.getMax_m_title());
		title.setSub_len(vo.getMax_sub_title());

		reqAssetsVO.setReqTitle(title);
	}

	/**
	 * @title: setReqImg
	 * @description: 转换原生广告请求图片
	 * @param reqAssetsVO
	 * @param vo
	 */
	private void setReqImg(ReqAssetsVO reqAssetsVO, FillNativeVO vo) {
		ReqImgVO img = new ReqImgVO();
		img.setH(vo.getH());
		img.setW(vo.getW());

		reqAssetsVO.setReqImg(img);
	}
}
