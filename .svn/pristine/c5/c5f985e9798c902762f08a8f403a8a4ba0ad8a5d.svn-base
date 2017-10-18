package com.gionee.ssp.exception.baidu;

public enum BaiduExpType {
	
	BAIDU_CREATIVETYPE_ERROR("1004001","创意类型不支持");
	
	/**异常编号**/
	private String code;
	/**异常信息**/
	private String msg;
	private BaiduExpType(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static String getMsg(String code){
		
		for(BaiduExpType exp: BaiduExpType.values()){
			if(exp.getCode().equals(code)){
				return exp.getMsg();
			}
		}
		return null;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
