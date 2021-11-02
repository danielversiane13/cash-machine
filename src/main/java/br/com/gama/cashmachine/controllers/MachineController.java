package br.com.gama.cashmachine.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gama.cashmachine.dto.MachineDto;
import br.com.gama.cashmachine.exceptions.ExceptionHandler;
import br.com.gama.cashmachine.forms.MachineForm;
import br.com.gama.cashmachine.services.MachineService;

@RestController
@RequestMapping("/machines")
public class MachineController {

	@Autowired
	private MachineService service;

	@GetMapping
	public ResponseEntity<Page<MachineDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<MachineDto> create(@RequestBody @Valid MachineForm form, UriComponentsBuilder uriBuilder) {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/machines/{machineId}").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping(value = "/{machineId}")
	public ResponseEntity<MachineDto> findById(@PathVariable UUID machineId) throws ExceptionHandler {
		var dto = service.findById(machineId);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/{machineId}")
	public ResponseEntity<MachineDto> update(@PathVariable UUID machineId, @RequestBody @Valid MachineForm form)
			throws ExceptionHandler {
		var dto = service.update(form, machineId);
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}

//	@DeleteMapping(value = "/{machineId}")
//	public ResponseEntity<Void> destroy(@PathVariable UUID machineId) throws ExceptionHandler {
//		service.deleteById(machineId);
//		return ResponseEntity.noContent().build();
//	}

}
