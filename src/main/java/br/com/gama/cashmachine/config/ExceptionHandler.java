package br.com.gama.cashmachine.config;

import org.springframework.http.HttpStatus;

public abstract class ExceptionHandler extends Throwable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;

	public ExceptionHandler() {
		super();
	}

	public ExceptionHandler(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
