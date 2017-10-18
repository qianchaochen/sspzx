package com.gionee.ssp.service.adx;

import com.gionee.ssp.vo.AdxAdslotInfoVo;
import com.wk.ssp.vo.FillingDataVO;

/**Adx信息服务层
 * @author dingyw
 *
 * 2017年9月6日
 */
public interface AdxInfoService {
	
	
	/**
	 * @param app_id
	 * @param ad_id
	 * @param vo
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public AdxAdslotInfoVo getAdxAdslotInfo(String app_id, String ad_id, FillingDataVO vo, int cp)throws Exception;

}
