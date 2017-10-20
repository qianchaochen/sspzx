package com.gionee.ssp.vo;

/**
 * 	直投广告来源,默认为0
 *  @author: wuxing
 *  @date: 2017年10月19日 下午5:23:53
 *
 */
public enum IpushSource {
	GIONEE(0, "金立广告"), ALI(1, "阿里广告");
	
	private IpushSource(int source, String desc) {
		this.source = source;
		this.desc = desc;
	}
	private int source;
	private String desc;
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
