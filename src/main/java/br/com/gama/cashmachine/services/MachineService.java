package br.com.gama.cashmachine.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.MachineDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.entities.MachineMoneyBills;
import br.com.gama.cashmachine.entities.MoneyBills;
import br.com.gama.cashmachine.exceptions.ExceptionHandler;
import br.com.gama.cashmachine.exceptions.NotFoundException;
import br.com.gama.cashmachine.factories.MachineFactory;
import br.com.gama.cashmachine.forms.MachineForm;
import br.com.gama.cashmachine.repositories.MachineMoneyBillsRepository;
import br.com.gama.cashmachine.repositories.MachineRepository;
import br.com.gama.cashmachine.repositories.MoneyBillsRepository;

@Service
public class MachineService {

	@Autowired
	private MachineRepository repository;

	@Autowired
	private MoneyBillsRepository moneyBillsRepository;

	@Autowired
	private MachineMoneyBillsRepository machineMoneyBillsRepository;

	public Page<MachineDto> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(MachineFactory::Create);
	}

	public MachineDto findById(UUID id) throws ExceptionHandler {
		Machine machine = repository.findById(id).orElseThrow(() -> new NotFoundException("Máquina não encontrado"));

		return MachineFactory.Create(machine);
	}

	public MachineDto create(MachineForm form) {
		Machine machine = MachineFactory.Create(form);
		repository.save(machine);

		List<MoneyBills> moneyBills = moneyBillsRepository.findAll();

		for (int i = 0; i < moneyBills.size(); i++) {
			machineMoneyBillsRepository.save(new MachineMoneyBills(machine, moneyBills.get(i), 0));
		}

		return MachineFactory.Create(machine);
	}

	public MachineDto update(MachineForm form, UUID id) throws ExceptionHandler {
		Machine machine = repository.findById(id).orElseThrow(() -> new NotFoundException("Máquina não encontrado"));

		machine.setName(form.name);
		machine.setUpdatedAt(LocalDateTime.now());
		repository.save(machine);

		return MachineFactory.Create(machine);
	}

	public void deleteById(UUID id) throws ExceptionHandler {
		Machine machine = repository.findById(id).orElseThrow(() -> new NotFoundException("Máquina não encontrado"));
		repository.delete(machine);
	}

}
