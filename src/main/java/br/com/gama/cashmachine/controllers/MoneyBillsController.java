package br.com.gama.cashmachine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.cashmachine.dto.MoneyBillsDto;
import br.com.gama.cashmachine.services.MoneyBillsService;

@RestController
@RequestMapping("/money-bills")
public class MoneyBillsController {

	@Autowired
	private MoneyBillsService service;

	@GetMapping
	public ResponseEntity<List<MoneyBillsDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

}
