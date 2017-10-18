package com.gionee.ssp.service.push.result;

import java.util.List;

import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;

/**
 * @author dingyw
 *
 * 2017年10月16日
 */
public interface PushResultListService {
	
	 /**
	 * @param list
	 * @param count
	 * @return
	 */
	public List<CampaignVO> getList(List<CampaignVO> list, int count) ;

}
