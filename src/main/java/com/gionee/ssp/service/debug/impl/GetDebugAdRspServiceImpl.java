package com.gionee.ssp.service.debug.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.debug.DebugCommonGenService;
import com.gionee.ssp.service.debug.DebugGenService;
import com.gionee.ssp.service.debug.GetDebugAdRspService;
import com.wk.model.adx.WKSSP.InteractionType;
import com.wk.ssp.vo.FillNativeVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class GetDebugAdRspServiceImpl implements GetDebugAdRspService{
	
	/**
	 * 生成服务层
	 */
	@Autowired
	DebugGenService debugGenService;
	
	/**
	 * 普通广告生成服务层
	 */
	@Autowired
	DebugCommonGenService debugCommonGenService;
	/**
	 * 测试广告点击跳转路径
	 */
	@Value("#{debug_config.DEBUG_CLICKURL}")
	protected String debug_click_url;
	
	
	@Override
	public void getAdRsp(SdkResponseVO rsp, SdkRequestVO req, FillingDataVO fillingDataVO) {

		SdkResponseAdVO vo = new SdkResponseAdVO();

		vo.setAdslot_id(req.getAdslot().getAdslot_id());
		vo.setCreative_type(CommonConstant.CreativeType.IMG.getValue());

		//获取广告交互类型
		this.getInteraction(fillingDataVO, vo);
		
		//生成广告类型 start
		List<FillNativeVO> nativeVO = fillingDataVO.getNativ();
		if(!ObjectUtils.isEmpty(nativeVO)){
			//生成原生广告
			debugGenService.genNative(vo, fillingDataVO);
		} else {
			//生成普通广告，非原生
			debugCommonGenService.genCommonAd(fillingDataVO, vo, req);
		}
		//生成广告类型 end
		
		List<String> imptrackers = new ArrayList<String>();
		vo.setImptrackers(imptrackers);

		List<String> clitrackers = new ArrayList<String>();
		vo.setClktrackers(clitrackers);

		rsp.setAds(vo);
	}
	
	/**创意动作类型
	 * @param fillingDataVO
	 * @param vo
	 */
	private void getInteraction(FillingDataVO fillingDataVO,SdkResponseAdVO vo ){
		//生成创意动作类型 start
		int interactionType = RandomUtils.nextInt(1, 3);
		List<Integer> bitcs = fillingDataVO.getBitc();
		if(!ObjectUtils.isEmpty(bitcs)){
			if(!bitcs.contains(InteractionType.DOWNLOAD_VALUE)){
				interactionType = CommonConstant.SDKInteractionType.WEB.getValue();
			}
		}
		//根据不同的交互类型
		if(CommonConstant.SDKInteractionType.DOWNLOAD.getValue() == interactionType){
			//下载类广告
			vo.setInteraction_type(CommonConstant.SDKInteractionType.DOWNLOAD.getValue());
			debugGenService.genDownAd(vo);
		} else {
			//展示类广告
			vo.setInteraction_type(CommonConstant.SDKInteractionType.WEB.getValue());
			vo.setBundle("");
			vo.setClkurl(debug_click_url);
		}
		//生成创意动作类型 end
	}


}
