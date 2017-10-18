package com.wk.ssp.vo.sdk;

import javax.validation.constraints.NotNull;

import com.gionee.common.vo.BaseVo;

/**
 * @description: 网络环境信息
 */
public class SdkNetworkVo extends BaseVo{

	/** 用户外网IP **/
	@NotNull(message = "105010")
	private String ip;
	
	/** 网络连接类型  0：未知连接，1：以太网，2：WiFi，3：未知蜂窝网络，4:2G，5:3G，6:4G**/
	@NotNull(message = "105020")
	private int connect_type;
	
	/** 运营商类型  0：未知运营商，1：中国移动，2：中国电信，3：中国联通，4：其他运营商**/
	@NotNull(message = "105030")
	private int carrier;
	
	/** 当前连接的运营商基站 **/
	private String cellular_id;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getConnect_type() {
		return connect_type;
	}

	public void setConnect_type(int connect_type) {
		this.connect_type = connect_type;
	}

	public int getCarrier() {
		return carrier;
	}

	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}

	public String getCellular_id() {
		return cellular_id;
	}

	public void setCellular_id(String cellular_id) {
		this.cellular_id = cellular_id;
	}
	
}
