package com.capg.exceptions;

public class InvalidEmailException extends Exception{
	String msg;
	public InvalidEmailException(String msg) {
		super();
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}

}

