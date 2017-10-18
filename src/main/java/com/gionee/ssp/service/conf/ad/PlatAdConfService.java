package com.gionee.ssp.service.conf.ad;

import com.gionee.ssp.vo.PlatAdConfVo;
import com.wk.ssp.vo.FillingDataVO;

/**
 * @author dingyw
 *
 * 2017年9月28日
 */
public interface PlatAdConfService {
	/**获取Baidu redis中的平台广告位配置信息
	 * @param app_id
	 * @param ad_id
	 * @return
	 */
	public PlatAdConfVo getBaiduAdConfVo(String app_id,String ad_id,FillingDataVO vo)throws Exception;
	
	/**获取 redis中的平台广告位配置信息
	 * @param app_id
	 * @param ad_id
	 * @return
	 */
	public PlatAdConfVo getInmobiAdConfVo(String app_id,String ad_id,FillingDataVO vo)throws Exception;
	
	/**获取redis中的平台广告位配置信息
	 * @param app_id
	 * @param ad_id
	 * @return
	 */
	public PlatAdConfVo getZakerAdConfVo(String app_id,String ad_id,FillingDataVO vo)throws Exception;
	
	/**获取redis中的平台广告位配置信息
	 * @param app_id
	 * @param ad_id
	 * @return
	 */
	public PlatAdConfVo getLingjiAdConfVo(String app_id,String ad_id,FillingDataVO vo)throws Exception;
	
	/**获取redis中的平台广告位配置信息
	 * @param app_id
	 * @param ad_id
	 * @return
	 */
	public PlatAdConfVo getToutiaoAdConfVo(String app_id,String ad_id,FillingDataVO vo)throws Exception;

}
