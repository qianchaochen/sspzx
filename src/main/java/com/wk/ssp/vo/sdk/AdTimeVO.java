package com.wk.ssp.vo.sdk;

import com.gionee.common.vo.BaseVo;

/**
 * 
 * @description 曝光时间vo
 * @author wuxing
 * @date 2017年4月5日
 *
 */
public class AdTimeVO extends BaseVo{
	/** 强制曝光时间 **/
	private int force;
	
	/** 跳过按钮时长 **/
	private int skip;
	
	/** 允许延迟上报时长 **/
    private int delay;
    
    /** 曝光时长 **/
    private int exposure;

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getExposure() {
		return exposure;
	}

	public void setExposure(int exposure) {
		this.exposure = exposure;
	}
}
