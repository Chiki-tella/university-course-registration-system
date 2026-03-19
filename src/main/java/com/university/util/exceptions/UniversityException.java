package com.university.util.exceptions;

public class UniversityException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UniversityException(String message) {
        super(message);
    }
    public UniversityException(String message, Throwable cause) {
        super(message, cause);
    }
}

