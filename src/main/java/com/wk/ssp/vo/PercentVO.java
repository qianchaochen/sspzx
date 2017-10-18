package com.wk.ssp.vo;

import com.gionee.common.vo.BaseVo;

/**
 * @description: 流量分配实体类
 */
public class PercentVO extends BaseVo{

	/** 厂商流量比例 **/
	private double firm;
	
	/** 玩咖流量比例 **/
	private double wk;
	
	/** 百度流量比例 **/
	private double baidu;
	
	/** 今日头条流量比例 **/
	private double jrtt;
	
	/** zaker流量比例 **/
	private double zaker;
	
	/** inmobi流量比例 **/
	private double inmobi;
	
	/** 灵集流量比例 **/
	private double lj;

	public double getFirm() {
		return firm;
	}

	public void setFirm(double firm) {
		this.firm = firm;
	}

	public double getWk() {
		return wk;
	}

	public void setWk(double wk) {
		this.wk = wk;
	}

	public double getBaidu() {
		return baidu;
	}

	public void setBaidu(double baidu) {
		this.baidu = baidu;
	}

	public double getZaker() {
		return zaker;
	}

	public double getInmobi() {
		return inmobi;
	}


	public void setZaker(double zaker) {
		this.zaker = zaker;
	}

	public void setInmobi(double inmobi) {
		this.inmobi = inmobi;
	}

	public double getLj() {
		return lj;
	}

	public void setLj(double lj) {
		this.lj = lj;
	}

	public double getJrtt() {
		return jrtt;
	}

	public void setJrtt(double jrtt) {
		this.jrtt = jrtt;
	}
}
