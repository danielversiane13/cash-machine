package br.com.gama.cashmachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundException extends ExceptionHandler {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}
