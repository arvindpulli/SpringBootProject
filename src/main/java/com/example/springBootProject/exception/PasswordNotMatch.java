package com.example.springBootProject.exception;

public class PasswordNotMatch extends RuntimeException {

	private String msg="Incorrect Password";

	public String getMsg() {
		return msg;
	}

	public PasswordNotMatch(String msg) {
		super();
		this.msg = msg;
	}
	
}
