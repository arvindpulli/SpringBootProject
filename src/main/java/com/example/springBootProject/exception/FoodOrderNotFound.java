package com.example.springBootProject.exception;

public class FoodOrderNotFound extends RuntimeException {

	private String msg="Given Order Id is not present";

	public String getMsg() {
		return msg;
	}

	public FoodOrderNotFound(String msg) {
		super();
		this.msg = msg;
	}

	
	
}
