package com.gionee.ssp.service.adx;

import java.util.Map;

import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.PercentVO;
import com.wk.ssp.vo.sdk.SdkResponseVO;

/**
 * @author dingyw
 *
 * 2017年9月8日
 */
public interface GetAdxNameService {
	/**
	 * @title: getADXName
	 * @description: 获取发送目标ADX
	 * @param percentVO
	 * @return
	 * @throws Exception
	 */
	public String getADXName(PercentVO percentVO) throws Exception;
	
	/**
	 * @title: 如果等级一样，那么根据流量配比进行分配
	 * @description: 获取目标ADX名
	 * @return
	 */
	public String getAdxName(FillingDataVO fillingDataVO,Map<String, SdkResponseVO> map)  throws Exception;

}
