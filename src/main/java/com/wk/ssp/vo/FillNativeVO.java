package com.wk.ssp.vo;

import com.gionee.common.vo.BaseVo;

/**
 * @description:
 */
public class FillNativeVO extends BaseVo{

	/** 原生广告类型 1：组图；2：小图；3：大图 **/
	private int type;
	
	/** 创意宽 **/
	private int w;
	
	/** 创意高 **/
	private int h;
	
	/** 主标题最大长度 **/
	private int max_m_title;
	
	/** 子标题最大长度 **/
	private int max_sub_title;
	
	/** 宽高比   **/
	private double w_h_percent;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getMax_m_title() {
		return max_m_title;
	}

	public void setMax_m_title(int max_m_title) {
		this.max_m_title = max_m_title;
	}

	public int getMax_sub_title() {
		return max_sub_title;
	}

	public void setMax_sub_title(int max_sub_title) {
		this.max_sub_title = max_sub_title;
	}

	public double getW_h_percent() {
		return w_h_percent;
	}

	public void setW_h_percent(double w_h_percent) {
		this.w_h_percent = w_h_percent;
	}
}
