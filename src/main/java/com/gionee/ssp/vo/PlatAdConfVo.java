package com.gionee.ssp.vo;

import com.gionee.common.vo.BaseVo;


/**第三方平台的ad配置信息
 * @author dingyw
 *
 * 2017年9月27日
 */
public class PlatAdConfVo extends BaseVo{
	
	/**第三方adx分配应用id**/
	private String appId;
	/**第三方adx分配广告位id**/
	private String adId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}



}
