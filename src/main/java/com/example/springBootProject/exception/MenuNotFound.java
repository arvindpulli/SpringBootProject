package com.example.springBootProject.exception;

public class MenuNotFound extends RuntimeException{

	public String msg="Menu is not found";

	public String getMsg() {
		return msg;
	}

	public MenuNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
