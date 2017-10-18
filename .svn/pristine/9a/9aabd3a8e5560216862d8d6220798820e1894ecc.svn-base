package com.wk.ssp.vo.sdk;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.gionee.common.vo.BaseVo;

/**
 * @description: 应用信息
 */
public class SdkAppVo extends BaseVo{

	/** 应用ID **/
	@NotNull(message = "103010")
	@NotBlank(message = "103011")
	private String app_id;
	
	/** 渠道ID **/
	private String channel_id;
	
	/** 应用版本 **/
	@NotBlank(message = "103030")
	private String app_version;
	
	/**
	 * 应用包名
	 */
	private String package_name;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
}
