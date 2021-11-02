package br.com.gama.cashmachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotAcceptableException extends ExceptionHandler {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public NotAcceptableException() {
		super();
	}

	public NotAcceptableException(String message) {
		super(HttpStatus.NOT_ACCEPTABLE, message);
	}

}
