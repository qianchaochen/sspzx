package com.gionee.sspzx.model;

public class TestUserMsg {
	public static void main(String[] args) {
		
		UserMsg.User john = UserMsg.User.newBuilder()
	    .setId(1234)
	    .setName("sunsnow")
	    .build();
		
		System.out.println(john.toString());
	}

}
