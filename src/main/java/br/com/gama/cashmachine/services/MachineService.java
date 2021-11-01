package br.com.gama.cashmachine.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.MachineDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.factories.MachineFactory;
import br.com.gama.cashmachine.forms.MachineForm;
import br.com.gama.cashmachine.repositories.MachineRepository;

@Service
public class MachineService {

	@Autowired
	private MachineRepository repository;

	public Page<MachineDto> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(MachineFactory::Create);
	}

	public MachineDto findById(UUID id) {
		var result = repository.findById(id);

		return result.isPresent() ? MachineFactory.Create(result.get()) : null;
	}

	public MachineDto create(MachineForm form) {
		Machine machine = MachineFactory.Create(form);
		repository.save(machine);

		return MachineFactory.Create(machine);
	}

	public MachineDto update(MachineForm form, UUID id) {
		var result = repository.findById(id);

		if (!result.isPresent()) {
			return null;
		}

		var customer = result.get();

		customer.setName(form.name);
		customer.setBalance(form.balance);
		customer.setUpdatedAt(LocalDateTime.now());

		repository.save(customer);

		return MachineFactory.Create(customer);
	}

	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

}
