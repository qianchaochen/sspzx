package com.gionee.ssp.service.preload.lock;

import java.util.List;

import com.wk.ssp.mvc.ipush.es.vo.CampaignVO;

/**
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface PreloadEsSearchService {
	/**搜索预加载的广告
	 * @param adId
	 * @return
	 * @throws Exception
	 */
	public List<CampaignVO> search(String adId) throws Exception;
}
