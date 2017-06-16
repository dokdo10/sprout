package com.dokdo.sprout.common.exception;

import com.dokdo.sprout.common.def.EnError;

public class SessionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EnError error;
	
	public SessionException(EnError error) {
		super(error.getMsg());
		this.error = error;
	}
	
	public EnError getError(){
		return error;
	}
}
