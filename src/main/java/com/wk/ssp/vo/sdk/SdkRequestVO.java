package com.wk.ssp.vo.sdk;import javax.validation.constraints.NotNull;import org.hibernate.validator.constraints.NotBlank;import com.gionee.common.vo.BaseVo;/** * @description: sdk请求信息 */public class SdkRequestVO extends BaseVo{	/** API版本号 **/	@NotNull(message = "102000")	@NotBlank(message = "102010")	private String api_version;		/** 应用信息 **/	@NotNull(message = "103000")	private SdkAppVo app;		/** 广告位信息 **/	@NotNull(message = "107000")	private SdkAdslotVO adslot;		/** 设备信息 **/	@NotNull(message = "104000")	private SdkDeviceVO device;		/** 网络信息 **/	@NotNull(message = "105000")	private SdkNetworkVo network;		/** 定位信息 **/	private SdkGpsVO gps;		private SDKInfoVO sdkInfoVO;	public String getApi_version() {		return api_version;	}	public void setApi_version(String api_version) {		this.api_version = api_version;	}	public SdkAppVo getApp() {		return app;	}	public void setApp(SdkAppVo app) {		this.app = app;	}	public SdkAdslotVO getAdslot() {		return adslot;	}	public void setAdslot(SdkAdslotVO adslot) {		this.adslot = adslot;	}	public SdkDeviceVO getDevice() {		return device;	}	public void setDevice(SdkDeviceVO device) {		this.device = device;	}	public SdkNetworkVo getNetwork() {		return network;	}	public void setNetwork(SdkNetworkVo network) {		this.network = network;	}	public SdkGpsVO getGps() {		return gps;	}	public void setGps(SdkGpsVO gps) {		this.gps = gps;	}	public SDKInfoVO getSdkInfoVO() {		return sdkInfoVO;	}	public void setSdkInfoVO(SDKInfoVO sdkInfoVO) {		this.sdkInfoVO = sdkInfoVO;	}	}