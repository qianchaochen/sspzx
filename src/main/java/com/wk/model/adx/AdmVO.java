package com.wk.model.adx;

import java.util.List;

/**
 * @description:
 * 
 * modified by zhengk 增加h5地址和deeplink
 */
public class AdmVO {

	/** 图片url **/
	private List<String> imgurl;
	
	/** 落地页或下载链接 **/
	private String landingurl;
	
	/** 标题 **/
	private String title;
	
	/** 子标题 **/
	private String subtitle;
	
	/** 图标 **/
	private String icon;
	
	/** 行为图标 **/
	private String iconaction;
	
	/** 推广源 **/
	private String source;
	
	/** 广告创意类型  1：信息流组图，2：信息流小图，3：信息流大图, 4:图片, 5:图文**/
	private int itemType;
	
	/** 素材md5 **/
	private List<String> imgmd5;
	
	//V 106 将素材的h5地址和deeplink全部返回，由ssp决定返回什么地址给sdk
    private String landingh5;
    private String deeplink;
	

	public List<String> getImgurl() {
		return imgurl;
	}

	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}

	public String getLandingurl() {
		return landingurl;
	}

	public void setLandingurl(String landingurl) {
		this.landingurl = landingurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconaction() {
		return iconaction;
	}

	public void setIconaction(String iconaction) {
		this.iconaction = iconaction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public List<String> getImgmd5() {
		return imgmd5;
	}

	public void setImgmd5(List<String> imgmd5) {
		this.imgmd5 = imgmd5;
	}

	public String getLandingh5() {
		return landingh5;
	}

	public void setLandingh5(String landingh5) {
		this.landingh5 = landingh5;
	}

	public String getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	
}
