package br.com.gama.cashmachine.factories;

import org.springframework.validation.FieldError;

import br.com.gama.cashmachine.dto.ValidationDto;

public class ValidationDtoFactory {

	public static ValidationDto Create(FieldError error) {
		ValidationDto dto = new ValidationDto();

		dto.message = error.getDefaultMessage();
		dto.field = error.getField();

		return dto;
	}

}
