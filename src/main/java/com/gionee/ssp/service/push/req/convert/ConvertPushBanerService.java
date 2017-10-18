package com.gionee.ssp.service.push.req.convert;

import java.util.List;

import com.wk.model.adx.WKSSP.AdSlot;
import com.wk.ssp.mvc.ipush.es.vo.CreativeMessageVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
public interface ConvertPushBanerService {
	
	/**
	 * @param adSlot
	 * @param list
	 */
	public void getCreativeBanner(AdSlot adSlot,List<CreativeMessageVO> list);

}
