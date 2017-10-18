package com.gionee.ssp.service.push.req.convert.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.push.req.convert.ConvertPushReqCreativeService;
import com.wk.model.adx.WKSSP.AdSlot;
import com.wk.model.adx.WKSSP.Banner;
import com.wk.model.adx.WKSSP.WKSSPRequest;
import com.wk.ssp.mvc.ipush.es.vo.CreativeMessageVO;
import com.wk.ssp.mvc.ipush.es.vo.QueryVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class ConvertPushReqCreativeServiceImpl extends BaseConvertPushReqCreativeServiceImpl implements ConvertPushReqCreativeService{
	
	@Override
	public int getCreativeInfo(WKSSPRequest wKSSPRequest, QueryVO queryVO) throws Exception{
        AdSlot adSlot = wKSSPRequest.getAdSlot();
        int ad_type = 0;
        List<CreativeMessageVO> list = new ArrayList<CreativeMessageVO>();
        
		 if (pushAdTypeService.isSpashScreen(adSlot)) { // 开屏
	            Banner banner = adSlot.getBanner();
	            CreativeMessageVO vo = new CreativeMessageVO();
	            vo.setW(banner.getW()); // 创意宽
	            vo.setH(banner.getH()); // 创意高
	            vo.setItem_types(Arrays.asList(4));
	            list.add(vo);
	            ad_type = CommonConstant.AD_TYPE.SPLASH_SCREEN.getValue();
	        } else if (pushAdTypeService.isInsertScreen(adSlot)) { // 插屏
	            Banner banner = adSlot.getBanner();
	            CreativeMessageVO vo = new CreativeMessageVO();
	            vo.setW(banner.getW()); // 创意宽
	            vo.setH(banner.getH()); // 创意高
	            vo.setItem_types(Arrays.asList(4));
	            list.add(vo);
	            ad_type = CommonConstant.AD_TYPE.INSERT_SCREEN.getValue();
	        } else if (pushAdTypeService.isBanner(adSlot)) { // 横幅
	        	convertPushBanerService.getCreativeBanner(adSlot, list);
	            ad_type = CommonConstant.AD_TYPE.BANNER.getValue();
	            
	        } else if (!ObjectUtils.isEmpty(adSlot.hasNative())) {
	        	 // 原生广告
	        	convertPushNativService.getNativInfo(adSlot, list);
	            ad_type = CommonConstant.AD_TYPE.NATIVE.getValue();
	        }
	     queryVO.setCreativeMessageVO(list); // 创意信息
		 return ad_type;
	}

}
