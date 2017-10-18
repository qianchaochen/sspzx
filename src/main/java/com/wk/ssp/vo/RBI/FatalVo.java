package com.wk.ssp.vo.RBI;

import com.gionee.common.vo.BaseVo;



/**异常打点数据对象
 * @author dingyw
 *
 * 2017年10月11日
 */
public class FatalVo extends BaseVo{
	/**
	 * 版本
	 */
	private String svr;
	/**
	 *设备 
	 */
	private String device;
	/**
	 *cuid 
	 */
	private String cuid ;
	/**
	 * 客户号ID
	 */
	private String client_id ;
	/**
	 *设备ID 
	 */
	private String device_id ;
	/**
	 * 应用ID
	 */
	private String app_id ;
	/**
	 * 错误信息
	 */
	private String error_info ; // 打点错误信息
	public String getSvr() {
		return svr;
	}
	public void setSvr(String svr) {
		this.svr = svr;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getError_info() {
		return error_info;
	}
	public void setError_info(String error_info) {
		this.error_info = error_info;
	}
	
	
}
