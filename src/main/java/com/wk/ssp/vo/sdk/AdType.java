package com.wk.ssp.vo.sdk;

public enum AdType {
	BANNER(1, "横幅"), SPLASH(2, "开屏"), INSTL(3, "插屏"), NATIVE(4, "原生");
	
	private int type;
	private String typeName;
	
	private AdType(int type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}
	public int getType() {
		return type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
