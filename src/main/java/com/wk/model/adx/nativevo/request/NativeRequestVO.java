package com.wk.model.adx.nativevo.request;

import java.util.List;

/**
 * @description: 原生广告请求模板
 */
public class NativeRequestVO {

	/** 原生广告当前版本 **/
	private String ver = "1.1";
	
	/** 原生广告限制条件 **/
	private List<ReqAssetsVO> reqAssets;
	
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public List<ReqAssetsVO> getReqAssets() {
		return reqAssets;
	}

	public void setReqAssets(List<ReqAssetsVO> reqAssets) {
		this.reqAssets = reqAssets;
	}
}
