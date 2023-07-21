package com.example.springBootProject.exception;

public class UserNotFound extends RuntimeException{

	private String msg="User with given id is not present";

	public String getMsg() {
		return msg;
	}

	public UserNotFound(String msg) {
		super();
		this.msg = msg;
	}
}
