package br.com.gama.cashmachine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.MoneyBillsDto;
import br.com.gama.cashmachine.factories.MoneyBillsFactory;
import br.com.gama.cashmachine.repositories.MoneyBillsRepository;

@Service
public class MoneyBillsService {

	@Autowired
	private MoneyBillsRepository repository;

	public List<MoneyBillsDto> findAll() {
		return repository.findAll().stream().map(MoneyBillsFactory::Create).collect(Collectors.toList());
	}

}
