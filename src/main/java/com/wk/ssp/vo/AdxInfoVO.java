package com.wk.ssp.vo;

import com.gionee.common.vo.BaseVo;

/**
 * @description: ADX 验证信息
 */
public class AdxInfoVO extends BaseVo{

	/** ADX注册得到的SSPID **/
	private String sspId;
	
	/** ADXf注册得到的token **/
	private String token;
	
	/** 连接的adxUrl **/
	private String adxUrl;
	
	/**adx编号**/
	private String adxCode;
	
	/**
	 * adx名字
	 */
	private String adxName;
	
	
	public AdxInfoVO(){
		
	}
	public AdxInfoVO(String adxName){
		this.adxName = adxName;
	}
	public AdxInfoVO(String adxName,String adxUrl){
		this.adxName = adxName;
		this.adxUrl = adxUrl;

	}

	public String getSspId() {
		return sspId;
	}

	public void setSspId(String sspId) {
		this.sspId = sspId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAdxUrl() {
		return adxUrl;
	}

	public void setAdxUrl(String adxUrl) {
		this.adxUrl = adxUrl;
	}

	public String getAdxCode() {
		return adxCode;
	}

	public void setAdxCode(String adxCode) {
		this.adxCode = adxCode;
	}

	public String getAdxName() {
		return adxName;
	}

	public void setAdxName(String adxName) {
		this.adxName = adxName;
	}

}
