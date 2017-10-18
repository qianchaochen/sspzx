package com.wk.ssp.vo;

/**
 * 所有adx的枚举
 * @description 
 * @author wuxing
 * @date 2017年4月18日
 *
 */
public enum AdxType {
	IPUSH(0, "直投"), 
	GIONEE(1, "金立"),
	WANKA(2, "玩咖"), 
	GDT(3, "广点通"), 
	BAIDU(6, "百度联盟"), 
	TOUTIAO(7, "今日头条"), 
	INMOBI(8, "inmobi"), 
	LINGJI(9, "灵集"), 
	ZAKER(10, "zaker");
	
	private int adx;
	private String adxName;
	
	private AdxType(int adx, String adxName) {
		this.adx = adx;
		this.adxName = adxName;
	}

	public int getAdx() {
		return adx;
	}

	public String getAdxName() {
		return adxName;
	}

	public void setAdx(int adx) {
		this.adx = adx;
	}

	public void setAdxName(String adxName) {
		this.adxName = adxName;
	}
}
