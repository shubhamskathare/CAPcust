package com.mindtree.exception;

public class CustomerNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String s) {
        super(s);
    }
}
