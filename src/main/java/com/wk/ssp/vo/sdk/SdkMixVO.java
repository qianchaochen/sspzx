package com.wk.ssp.vo.sdk;

import com.gionee.common.vo.BaseVo;

/**
 * @description: H5创意VO
 */
public class SdkMixVO extends BaseVo{

	/** 主标题 **/
	private String title;
	
	/** 子标题 **/
	private String sub_title;
	
	/** icon图片url **/
	private String imgurl;
	
	/** 行为图标url **/
	private String acimgurl;
	
	/** 背景色值 **/
	private String bg_color = "333333";
	
	/** 文字色值 **/
	private String text_color = "ffffff";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getAcimgurl() {
		return acimgurl;
	}

	public void setAcimgurl(String acimgurl) {
		this.acimgurl = acimgurl;
	}

	public String getBg_color() {
		return bg_color;
	}

	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}

	public String getText_color() {
		return text_color;
	}

	public void setText_color(String text_color) {
		this.text_color = text_color;
	}
	
}
