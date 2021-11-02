package br.com.gama.cashmachine.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.cashmachine.config.ExceptionHandler;
import br.com.gama.cashmachine.dto.CurrentAccountWithdrawDto;
import br.com.gama.cashmachine.forms.CurrentAccountWithdrawForm;
import br.com.gama.cashmachine.services.CurrentAccountService;

@RestController
@RequestMapping("/current-account")
public class CurrentAccountController {

	@Autowired
	private CurrentAccountService service;

	@PostMapping(value = "/{machineId}/withdraw")
	public ResponseEntity<List<CurrentAccountWithdrawDto>> create(@PathVariable UUID machineId,
			@RequestBody @Valid CurrentAccountWithdrawForm form) throws ExceptionHandler {

		var dto = service.withdraw(machineId, form);
		return ResponseEntity.ok(dto);
	}

}
