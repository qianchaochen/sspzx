package com.gionee.ssp.vo;

/**
 * 第三方adx对应的应用信息
 * @description 
 * @author wuxing
 * @date 2017年4月17日
 *
 */
public class AdxAdslotInfoVo {
	private String appId;	//第三方应用ID
	private String adslotId;	//第三方广告位ID
	private String appPackage;	//应用包名
	
	public AdxAdslotInfoVo(String appId, String adslotId, String appPackage) {
		this.appId = appId;
		this.adslotId = adslotId;
		this.appPackage = appPackage;
	}
	public String getAppId() {
		return appId;
	}
	public String getAdslotId() {
		return adslotId;
	}
	public String getAppPackage() {
		return appPackage;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public void setAdslotId(String adslotId) {
		this.adslotId = adslotId;
	}
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
}
