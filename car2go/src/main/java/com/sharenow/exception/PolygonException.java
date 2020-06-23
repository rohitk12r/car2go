package com.sharenow.exception;

public class PolygonException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3398827788130099944L;

	public PolygonException() {
		super();
	}

	public PolygonException(String error, String message, Throwable cause) {
		super(error, message, cause);
	}

	public PolygonException(String message) {
		super(message);
	}
}
