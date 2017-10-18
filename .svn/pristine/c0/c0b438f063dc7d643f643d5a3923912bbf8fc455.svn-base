package com.gionee.ssp.service.req.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.req.ConvertReqBanAdvertiserService;
import com.wk.model.adx.WKSSP.WKSSPRequest.Builder;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年9月7日
 */
@Service
public class ConvertReqBanAdvertiserServiceImpl implements ConvertReqBanAdvertiserService{
	
	public void setBanAdvertiser(Builder reqBuilder,FillingDataVO vo){
		List<String> badvs = vo.getBadv();
		if (null != badvs && badvs.size() > 0) {
			String badv;
			Iterator<String> it_badv = badvs.iterator();
			while (it_badv.hasNext()) {
				badv = it_badv.next();
				reqBuilder.addBadv(badv);
			}
		}
	}

}
