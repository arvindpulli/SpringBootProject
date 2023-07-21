package com.example.springBootProject.exception;

public class ItemNotFound extends RuntimeException{

	private String msg="Given Item Id is not present";

	public String getMsg() {
		return msg;
	}

	public ItemNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
