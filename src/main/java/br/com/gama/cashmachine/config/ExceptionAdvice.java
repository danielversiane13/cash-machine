package br.com.gama.cashmachine.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.gama.cashmachine.dto.ExceptionHandlerDto;
import br.com.gama.cashmachine.exceptions.BadRequestException;
import br.com.gama.cashmachine.exceptions.NotAcceptableException;
import br.com.gama.cashmachine.exceptions.NotFoundException;
import br.com.gama.cashmachine.factories.ExceptionHandlerFactory;
import br.com.gama.cashmachine.dto.ValidationDto;
import br.com.gama.cashmachine.factories.ValidationDtoFactory;

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

	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationDto> handle(MethodArgumentNotValidException exception) {
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();

		return errors.stream().map(ValidationDtoFactory::Create).collect(Collectors.toList());
	}

}
