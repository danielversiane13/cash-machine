package br.com.gama.cashmachine.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.gama.cashmachine.dto.ExceptionHandlerDto;
import br.com.gama.cashmachine.exceptions.BadRequestException;
import br.com.gama.cashmachine.exceptions.NotAcceptableException;
import br.com.gama.cashmachine.exceptions.NotFoundException;
import br.com.gama.cashmachine.factories.ExceptionHandlerFactory;

@RestControllerAdvice
public class ExceptionAdvice {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public ExceptionHandlerDto handle(BadRequestException exception) {
		return ExceptionHandlerFactory.Create(exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ExceptionHandlerDto handle(NotFoundException exception) {
		return ExceptionHandlerFactory.Create(exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(NotAcceptableException.class)
	public ExceptionHandlerDto handle(NotAcceptableException exception) {
		return ExceptionHandlerFactory.Create(exception.getMessage());
	}

}
