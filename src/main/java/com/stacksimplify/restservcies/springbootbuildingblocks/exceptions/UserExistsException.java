package com.stacksimplify.restservcies.springbootbuildingblocks.exceptions;

public class UserExistsException extends Exception {

	
	private static final long serialVersionUID = 7702729648919544298L;

	public UserExistsException(String message) {
		super(message);
	}

}
