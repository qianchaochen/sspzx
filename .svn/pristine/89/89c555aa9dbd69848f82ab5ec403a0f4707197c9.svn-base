package com.gionee.ssp.service.push.req.convert.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.common.CommonConstant;
import com.gionee.ssp.service.push.req.convert.ConvertPushBanerService;
import com.wk.model.adx.WKSSP.AdSlot;
import com.wk.model.adx.WKSSP.Banner;
import com.wk.model.adx.WKSSP.CreativeType;
import com.wk.ssp.mvc.ipush.es.vo.CreativeMessageVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
@Service
public class ConvertPushBanerServiceImpl implements ConvertPushBanerService{
	
	@Override
	public void getCreativeBanner(AdSlot adSlot,List<CreativeMessageVO> list){
        List<Integer> itemTypes = new ArrayList<Integer>();
        Banner banner = adSlot.getBanner();
        CreativeMessageVO vo = new CreativeMessageVO();
        vo.setW(banner.getW()); // 创意宽
        vo.setH(banner.getH()); // 创意高
        for (CreativeType cType : banner.getCTypeList()) {
            if (CreativeType.TEXT_ICON.equals(cType)) { // 图文
                itemTypes.add(CommonConstant.MATERIAL_TYPE.IMG_TEXT.getValue());
            } else { // 图片
                itemTypes.add(CommonConstant.MATERIAL_TYPE.ONLY_IMG.getValue());
            }

        }
        vo.setItem_types(itemTypes);
        list.add(vo);
   
	}

}
