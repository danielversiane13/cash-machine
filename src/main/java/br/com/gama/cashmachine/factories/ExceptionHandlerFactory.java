package br.com.gama.cashmachine.factories;

import br.com.gama.cashmachine.dto.ExceptionHandlerDto;

public class ExceptionHandlerFactory {

	public static ExceptionHandlerDto Create(String message) {
		ExceptionHandlerDto dto = new ExceptionHandlerDto();

		dto.message = message;

		return dto;
	}

}
