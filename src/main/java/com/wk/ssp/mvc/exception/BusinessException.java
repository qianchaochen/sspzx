package com.wk.ssp.mvc.exception;

public class BusinessException extends RuntimeException {

	/**
	 *  @description: 业务异常类
	 */
	private static final long serialVersionUID = 820300366725003300L;

	public BusinessException(int frdMessage) {
		super(String.valueOf(frdMessage));
	}
	
	public BusinessException(String frdMessage) {
		super(frdMessage);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(Throwable throwable, String frdMessage) {
		super(createFriendlyErrMsg(frdMessage + ":" + throwable.getMessage()));
	}

	private static String createFriendlyErrMsg(String msgBody) {
		String prefixStr = "异常前缀：[";
		String suffixStr = "]:异常后缀";
		StringBuffer friendlyErrMsg = new StringBuffer("");
		friendlyErrMsg.append(prefixStr);
		friendlyErrMsg.append(msgBody);
		friendlyErrMsg.append(suffixStr);
		return friendlyErrMsg.toString();
	}

	public static void main(String[] args) {
		System.out.println(new BusinessException(new NullPointerException(
				"some message from nullpointer excepiton."), "wrap infor..."));
	}
}
