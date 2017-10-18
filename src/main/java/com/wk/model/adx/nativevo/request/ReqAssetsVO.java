package com.wk.model.adx.nativevo.request;

/**
 * @description: 原生广告限制条件
 */
public class ReqAssetsVO {

	/** 原生广告类型。1：信息流组图，2：信息流小图，3：信息流大图 **/
	private int type;

	/** 请求标题限制条件 **/
	private ReqTitleVO reqTitle;

	/** 请求图片限制条件 **/
	private ReqImgVO reqImg;

	public ReqTitleVO getReqTitle() {
		return reqTitle;
	}

	public void setReqTitle(ReqTitleVO reqTitle) {
		this.reqTitle = reqTitle;
	}

	public ReqImgVO getReqImg() {
		return reqImg;
	}

	public void setReqImg(ReqImgVO reqImg) {
		this.reqImg = reqImg;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
