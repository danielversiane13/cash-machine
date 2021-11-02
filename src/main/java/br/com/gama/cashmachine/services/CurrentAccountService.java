package br.com.gama.cashmachine.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.CurrentAccountWithdrawDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.entities.MoneyBills;
import br.com.gama.cashmachine.exceptions.BadRequestException;
import br.com.gama.cashmachine.exceptions.ExceptionHandler;
import br.com.gama.cashmachine.exceptions.NotAcceptableException;
import br.com.gama.cashmachine.exceptions.NotFoundException;
import br.com.gama.cashmachine.factories.CurrentAccountWithdrawFactory;
import br.com.gama.cashmachine.forms.CurrentAccountWithdrawForm;
import br.com.gama.cashmachine.repositories.MachineRepository;
import br.com.gama.cashmachine.repositories.MoneyBillsRepository;

@Service
public class CurrentAccountService {

	@Autowired
	private MachineRepository machineRepository;

	@Autowired
	private MoneyBillsRepository moneyBillsRepository;

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
		if (machine.getBalance() < form.value) {
			throw new NotAcceptableException("Caixa temporariamente indisponível");
		}

		int _value = form.value;
		List<MoneyBills> moneyBills = moneyBillsRepository.findByActiveTrueOrderByValueDesc();
		List<CurrentAccountWithdrawDto> withdrawList = new ArrayList<CurrentAccountWithdrawDto>();

		for (int i = 0; i < moneyBills.size(); i++) {
			MoneyBills money = moneyBills.get(i);
			int quantity = _value / money.getValue();
			_value = _value - (money.getValue() * quantity);

			// TODO: Add validation quantity of money bills

			// Case needs values only > 0
//			if (quantity == 0) {
//				continue;
//			}

			withdrawList.add(CurrentAccountWithdrawFactory.Create(money.getLabel(), quantity));

			// Case needs values only > 0
//			if (_value == 0) {
//				break;
//			}
		}

		// TODO: Add validation if _value > 0, throw error 406

		machine.setBalance(machine.getBalance() - form.value);
		machineRepository.save(machine);

		return withdrawList;
	}

}
