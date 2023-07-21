package com.example.springBootProject.exception;

public class ProductNotFound extends RuntimeException{

	private String msg="Product Id is not Found ";

	public String getMsg() {
		return msg;
	}

	public ProductNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
