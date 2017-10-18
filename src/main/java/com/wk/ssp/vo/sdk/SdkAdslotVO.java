package com.wk.ssp.vo.sdk;

import javax.validation.constraints.NotNull;

import com.gionee.common.vo.BaseVo;

/**
 * @description: 广告位信息
 */
public class SdkAdslotVO extends BaseVo{

	/** 广告位ID **/
	@NotNull(message = "107010")
	private String adslot_id;
	
	/** 广告位宽 **/
	@NotNull(message = "107020")
	private int adslot_w;
	
	/** 广告位高 **/
	@NotNull (message = "107030")
	private int adslot_h;

	public String getAdslot_id() {
		return adslot_id;
	}

	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}

	public int getAdslot_w() {
		return adslot_w;
	}

	public void setAdslot_w(int adslot_w) {
		this.adslot_w = adslot_w;
	}

	public int getAdslot_h() {
		return adslot_h;
	}

	public void setAdslot_h(int adslot_h) {
		this.adslot_h = adslot_h;
	}

	
	
}
