package com.sharenow.exception;

public class VehicleException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4197994835567199778L;

	public VehicleException() {
		super();
	}

	public VehicleException(String message) {
		super(message);
	}
	
	public VehicleException(String error, String message, Throwable cause) {
		super(error, message, cause);
	}
}
