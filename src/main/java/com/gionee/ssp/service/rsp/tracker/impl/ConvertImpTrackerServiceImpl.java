package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertImpTrackerService;
import com.wk.model.adx.WKSSP;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertImpTrackerServiceImpl implements ConvertImpTrackerService{
	
	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp,WKSSP.Ad ad){
		// 填充上游系统展示监播
		List<String> result = new ArrayList<String>();
		List<String> list = ad.getImptrackersList();
		if (list != null && list.size() > 0) {
			String imp;
			Iterator<String> it_imp = list.iterator();
			while (it_imp.hasNext()) {
				imp = it_imp.next();
				result.add(imp);
			}
		}
		rsp.setImptrackers(result);
	}

}
