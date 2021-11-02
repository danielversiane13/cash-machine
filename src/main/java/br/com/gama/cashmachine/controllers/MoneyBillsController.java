package br.com.gama.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.cashmachine.dto.MoneyBillsDto;
import br.com.gama.cashmachine.services.MoneyBillsService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/money-bills")
public class MoneyBillsController {

	@Autowired
	private MoneyBillsService service;

	@GetMapping
	@ApiOperation(value = "List money bills")
	public ResponseEntity<List<MoneyBillsDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

}
