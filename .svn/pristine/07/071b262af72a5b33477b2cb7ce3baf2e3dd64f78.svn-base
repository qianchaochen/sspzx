package com.gionee.ssp.service.debug.impl;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.debug.DebugCommonGenService;
import com.gionee.ssp.service.debug.DebugGenService;
import com.gionee.ssp.service.debug.DebugImageService;
import com.wk.ssp.vo.FillBannerVO;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class DebugCommonGenServiceImpl implements DebugCommonGenService{
	
	/**
	 * 生成服务层
	 */
	@Autowired
	DebugGenService debugGenService;
	/**
	 * debug图片服务层
	 */
	@Autowired
	DebugImageService debugImageService;
	
	@Override
	public void genCommonAd(FillingDataVO fillingDataVO,SdkResponseAdVO vo,SdkRequestVO req){

		FillBannerVO bannerVO = fillingDataVO.getBanner();
		
		if(CommonConstant.IS_TRUE.FALSE.getValue()==fillingDataVO.getInstl() && 
				CommonConstant.IS_TRUE.FALSE.getValue() == fillingDataVO.getIs_splash() && 
				!ObjectUtils.isEmpty(bannerVO)){
			
			List<Integer> types = bannerVO.getType();
			if(null != types){
				
				if(types.contains(CommonConstant.BANNER_MATERIAL_TYPE.IMG_TEXT.getValue())){
					
					int type = RandomUtils.nextInt(0, 2);
					if(types.size() ==1 || CommonConstant.BANNER_MATERIAL_TYPE.IMG_TEXT.getValue() == type){
						//生成图文混合广告
						debugGenService.genMix(vo, fillingDataVO);
						return ;
					}
				}
			}
		}
		//横幅，插屏，开屏
		debugImageService.setDebugImgUrl(vo, fillingDataVO, req);
	
	}

}
