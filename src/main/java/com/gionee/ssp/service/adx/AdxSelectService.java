package com.gionee.ssp.service.adx;

import com.wk.ssp.vo.AdxInfoVO;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年4月22日
 */
public interface AdxSelectService {
	/**
	 * @title: getADXDestination
	 * @description: 获取目标ADX名
	 * @return
	 */
	public AdxInfoVO getADXDestination(FillingDataVO fillingDataVO)  throws Exception;
	

}
