package com.gionee.ssp.vo.log;

import com.gionee.common.vo.BaseVo;

/**
 * @author dingyw
 *
 * 2017年10月11日
 */
public class ShieldAdLogVo extends BaseVo{
	
	/**
	 * 版本号
	 */
	private String svr;
	/**
	 * app_id
	 */
	private String app_id;
	/**
	 * 广告位id
	 */
	private String ad_id;
	/**
	 * imei
	 */
	private String imei;
	/**
	 *屏蔽原因 
	 */
	private String reason;
	public String getSvr() {
		return svr;
	}
	public void setSvr(String svr) {
		this.svr = svr;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
