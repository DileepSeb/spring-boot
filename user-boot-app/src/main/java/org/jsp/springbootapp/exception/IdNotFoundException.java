package org.jsp.springbootapp.exception;

public class IdNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
      
	@Override
	public String getMessage() {
		return "the Id is invalid";
	}
}
