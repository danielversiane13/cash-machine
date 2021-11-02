package br.com.gama.cashmachine.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestException extends ExceptionHandler {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

}
