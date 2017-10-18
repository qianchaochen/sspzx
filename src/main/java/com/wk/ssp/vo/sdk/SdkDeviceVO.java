package com.wk.ssp.vo.sdk;

import javax.validation.constraints.NotNull;

import com.gionee.common.vo.BaseVo;

/**
 * @description: 设备信息
 */
public class SdkDeviceVO extends BaseVo{

	/** 设备类型 1：移动设备，2：个人计算机，3：连接电视，4：手机，5：平板，6：连通装置，7：机顶盒 **/
	@NotNull(message = "")
	private int device_type;
	
	/** 操作系统类型全小写 例：android **/
    
	private String os_type = "android";
	
	/** 操作系统版本 **/
	@NotNull(message = "104020")
	private String os_version;
	
	/** 设备厂商名称 **/
	@NotNull(message = "104050")
	private String vendor;
	
	/** 设备型号 **/
	@NotNull(message = "104060")
	private String model;
	
	/** Android设备系统ID **/
	@NotNull(message = "104070")
	private String android_id;
	
	/** Android手机设备的IMEI号，经过MD5加密 **/
	@NotNull(message = "104081")
	private String imei_md5;
	
	/** 设备屏幕宽度 **/
	@NotNull(message = "104100")
	private int w;
	
	/** 设备屏幕高度 **/
	@NotNull(message = "104110")
	private int h;
	
	/** 设备mac号 **/
//	@NotNull(message = "104090")
	private String mac;
	
	private String ua;
	
	private int dpi;
	
	private String web_ua;

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

	public String getOs_type() {
		return os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}

	public String getImei_md5() {
		return imei_md5;
	}

	public void setImei_md5(String imei_md5) {
		this.imei_md5 = imei_md5;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

	public String getWeb_ua() {
		return web_ua;
	}

	public void setWeb_ua(String web_ua) {
		this.web_ua = web_ua;
	}
}
