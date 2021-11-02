package br.com.gama.cashmachine.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.WithdrawDto;
import br.com.gama.cashmachine.factories.WithdrawFactory;
import br.com.gama.cashmachine.repositories.WithdrawRepository;

@Service
public class WithdrawService {

	@Autowired
	private WithdrawRepository repository;

	public Page<WithdrawDto> findByMachine(Pageable pageable, UUID machineId) {
		return repository.findByMachineId(pageable, machineId).map(WithdrawFactory::Create);
	}

}
