package br.com.gama.cashmachine.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.cashmachine.dto.CurrentAccountWithdrawDto;
import br.com.gama.cashmachine.dto.MachineDto;
import br.com.gama.cashmachine.exceptions.ExceptionHandler;
import br.com.gama.cashmachine.forms.CurrentAccountDepositForm;
import br.com.gama.cashmachine.forms.CurrentAccountWithdrawForm;
import br.com.gama.cashmachine.services.CurrentAccountService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/current-account")
public class CurrentAccountController {

	@Autowired
	private CurrentAccountService service;

	@GetMapping(value = "/{machineId}")
	@ApiOperation(value = "Get quantity of each money bills")
	public ResponseEntity<List<CurrentAccountWithdrawDto>> balance(@PathVariable UUID machineId)
			throws ExceptionHandler {
		var dto = service.balance(machineId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping(value = "/{machineId}/withdraw")
	@ApiOperation(value = "Withdraw balance")
	public ResponseEntity<List<CurrentAccountWithdrawDto>> withdraw(@PathVariable UUID machineId,
			@RequestBody @Valid CurrentAccountWithdrawForm form) throws ExceptionHandler {

		var dto = service.withdraw(machineId, form);
		return ResponseEntity.ok(dto);
	}

	@PostMapping(value = "/{machineId}/money-bills/{moneyBillsId}")
	@ApiOperation(value = "Add quantity for one money bills")
	public ResponseEntity<MachineDto> deposit(@RequestBody @Valid CurrentAccountDepositForm form,
			@PathVariable UUID machineId, @PathVariable UUID moneyBillsId) throws ExceptionHandler {

		var dto = service.deposit(machineId, moneyBillsId, form);

		return ResponseEntity.ok(dto);
	}

}
