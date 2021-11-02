package br.com.gama.cashmachine.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.cashmachine.dto.WithdrawDto;
import br.com.gama.cashmachine.services.WithdrawService;

@RestController
@RequestMapping("/machines")
public class MachineWithdrawController {

	@Autowired
	private WithdrawService service;

	@GetMapping(value = "/{machineId}/withdraws")
	public ResponseEntity<Page<WithdrawDto>> findAll(Pageable pageable, @PathVariable UUID machineId) {
		return ResponseEntity.ok(service.findByMachine(pageable, machineId));
	}

}
