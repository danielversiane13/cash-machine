package br.com.gama.cashmachine.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.CurrentAccountWithdrawDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.exceptions.ExceptionHandler;
import br.com.gama.cashmachine.exceptions.NotFoundException;
import br.com.gama.cashmachine.factories.CurrentAccountWithdrawFactory;
import br.com.gama.cashmachine.forms.CurrentAccountWithdrawForm;
import br.com.gama.cashmachine.repositories.MachineRepository;

@Service
public class CurrentAccountService {

	@Autowired
	private MachineRepository machineRepository;

	public List<CurrentAccountWithdrawDto> withdraw(UUID machineId, CurrentAccountWithdrawForm form)
			throws ExceptionHandler {
		boolean valueIsInvalid = form.value <= 0 || form.value != (int) form.value || (form.value % 5) != 0;

		if (valueIsInvalid) {
			throw new BadRequestException("Valor de saque inválido");
		}

		var result = machineRepository.findById(machineId);
		if (!result.isPresent()) {
			throw new NotFoundException("Máquina não encontrado");
		}

		Machine machine = result.get();

		// TODO: Add withdraw logic

		List<CurrentAccountWithdrawDto> arr = new ArrayList<CurrentAccountWithdrawDto>();

		arr.add(CurrentAccountWithdrawFactory.Create("5", 1));
		arr.add(CurrentAccountWithdrawFactory.Create("10", 2));
		arr.add(CurrentAccountWithdrawFactory.Create("20", 3));
		arr.add(CurrentAccountWithdrawFactory.Create("50", 4));
		arr.add(CurrentAccountWithdrawFactory.Create("100", 0));

		return arr;
	}

}
