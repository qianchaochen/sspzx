package com.gionee.ssp.service.ad;

import com.wk.ssp.vo.FillingDataVO;

/**广告位信息服务层
 * 根据redis内容进行相应处理
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface AdInfoService {

	/**
	 * @title: getCreativeType
	 * @description: 获取广告位创意类型
	 * @param fillingDataVO
	 * @return
	 */
	public int getAdType(FillingDataVO fillingDataVO);
	
	/**
	 * @title: isIpush
	 * @description: 判断是否进行直投广告
	 * @param fillingDataVO
	 * @return
	 */
	public boolean isIpush(FillingDataVO fillingDataVO);
}
