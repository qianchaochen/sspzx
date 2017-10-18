package com.gionee.ssp.service.req.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.req.ConvertReqBanAdCatalogService;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqBanAdCatalogServiceImpl implements ConvertReqBanAdCatalogService{
	
	public void setBanAdCatalog(Builder reqBuilder,FillingDataVO vo){
		// 转换禁用广告类别
		List<String> bcats = vo.getBcat();
		if (null != bcats && bcats.size() > 0) {
			Iterator<String> it_bcat = bcats.iterator();
			String bcat;
			while (it_bcat.hasNext()) {
				bcat = it_bcat.next();
				reqBuilder.addBcat(bcat);
				}
			}
	}
}
