package com.gionee.ssp.service.rsp.tracker.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gionee.ssp.service.rsp.tracker.ConvertClkTrackerService;
import com.wk.model.adx.WKSSP;
import com.wk.ssp.vo.sdk.SdkResponseAdVO;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
@Service
public class ConvertClkTrackerServiceImpl implements ConvertClkTrackerService{
	
	@Override
	public void convertTrackerInfo(SdkResponseAdVO rsp,WKSSP.Ad ad){
		// 填充上游系统点击监播
		List<String> result = new ArrayList<String>();
		List<String> list = ad.getClktrackersList();
		if (list != null && list.size() > 0) {
			String clk;
			Iterator<String> it_clk = list.iterator();
			while (it_clk.hasNext()) {
				clk = it_clk.next();
				result.add(clk);
			}
		}
		rsp.setClktrackers(result);
	}

}
