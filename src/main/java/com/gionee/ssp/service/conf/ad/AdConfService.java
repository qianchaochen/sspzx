package com.gionee.ssp.service.conf.ad;

import com.gionee.ssp.vo.SSPAdShieldConfigVo;
import com.wk.ssp.vo.FillingDataVO;
import com.wk.ssp.vo.sdk.SdkRequestVO;


/**广告配置服务层
 * @author dingyw
 *
 * 2017年9月5日
 */
public interface AdConfService {


	/**
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public FillingDataVO getAdConfData(SdkRequestVO req) throws Exception;

	/**
	 * @param appId
	 * @param adId
	 * @return
	 * @throws Exception
	 */
	public FillingDataVO getAdConfData(String appId ,String adId) throws Exception;

	/**
	 * @describe 获取广告屏蔽配置
	 * @param key
	 * @return
	 * @throws Exception
	 * @author XuLei
	 * @date 2017年8月15日 上午11:10:00 
	 */
	public SSPAdShieldConfigVo getAdShieldCfg(String key) throws Exception;

}
