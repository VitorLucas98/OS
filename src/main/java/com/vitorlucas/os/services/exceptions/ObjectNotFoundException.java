package com.vitorlucas.os.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}
}
