package com.gionee.ssp.service.push.rsp.convert;

import com.wk.model.adx.WKSSP.Ad;
import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;
import com.wk.ssp.mvc.ipush.es.vo.CreativeVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
public interface PushConvertAdmService {
	
	/**
	 * @param creativeVO
	 * @param vo
	 * @param adBuilder
	 * @throws Exception
	 */
	public void getAdm(CreativeVO creativeVO,CampaignVO vo,Ad.Builder adBuilder) throws Exception;

}
