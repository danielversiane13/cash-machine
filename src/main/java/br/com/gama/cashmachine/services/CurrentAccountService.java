package br.com.gama.cashmachine.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.cashmachine.dto.CurrentAccountWithdrawDto;
import br.com.gama.cashmachine.dto.MachineDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.entities.MachineMoneyBills;
import br.com.gama.cashmachine.entities.MoneyBills;
import br.com.gama.cashmachine.exceptions.BadRequestException;
import br.com.gama.cashmachine.exceptions.ExceptionHandler;
import br.com.gama.cashmachine.exceptions.NotAcceptableException;
import br.com.gama.cashmachine.exceptions.NotFoundException;
import br.com.gama.cashmachine.factories.CurrentAccountWithdrawFactory;
import br.com.gama.cashmachine.factories.MachineFactory;
import br.com.gama.cashmachine.factories.WithdrawFactory;
import br.com.gama.cashmachine.forms.CurrentAccountDepositForm;
import br.com.gama.cashmachine.forms.CurrentAccountWithdrawForm;
import br.com.gama.cashmachine.repositories.MachineMoneyBillsRepository;
import br.com.gama.cashmachine.repositories.MachineRepository;
import br.com.gama.cashmachine.repositories.MoneyBillsRepository;
import br.com.gama.cashmachine.repositories.WithdrawRepository;

@Service
public class CurrentAccountService {

	@Autowired
	private MachineRepository machineRepository;

	@Autowired
	private MoneyBillsRepository moneyBillsRepository;

	@Autowired
	private MachineMoneyBillsRepository machineMoneyRepository;

	@Autowired
	private WithdrawRepository withdrawRepository;

	public List<CurrentAccountWithdrawDto> balance(UUID machineId) throws ExceptionHandler {
		Machine machine = machineRepository.findById(machineId)
				.orElseThrow(() -> new NotFoundException("Máquina não encontrado"));

		return machine.getMoneyBills().stream()
				.map((mm) -> CurrentAccountWithdrawFactory.Create(mm.getMoneyBills().getLabel(), mm.getQuantity()))
				.collect(Collectors.toList());
	}

	public List<CurrentAccountWithdrawDto> withdraw(UUID machineId, CurrentAccountWithdrawForm form)
			throws ExceptionHandler {
		boolean valueIsInvalid = form.value <= 0 || form.value != (int) form.value || (form.value % 5) != 0;

		if (valueIsInvalid) {
			throw new BadRequestException("Valor de saque inválido");
		}

		Machine machine = machineRepository.findById(machineId)
				.orElseThrow(() -> new NotFoundException("Máquina não encontrado"));

		if (machine.getBalance() < form.value) {
			throw new NotAcceptableException("Caixa temporariamente indisponível");
		}

		int _value = form.value;
		List<MoneyBills> moneyBills = moneyBillsRepository.findByActiveTrueOrderByValueDesc();
		List<CurrentAccountWithdrawDto> withdrawList = new ArrayList<CurrentAccountWithdrawDto>();
		Set<MachineMoneyBills> machineMoney = machine.getMoneyBills();
		List<MachineMoneyBills> machineMoneyToSave = new ArrayList<MachineMoneyBills>();

		for (int i = 0; i < moneyBills.size(); i++) {
			MoneyBills money = moneyBills.get(i);
			int quantity = _value / money.getValue();

			// Case needs values only > 0
//			if (quantity == 0) {
//				continue;
//			}

			if (quantity > 0) {
				MachineMoneyBills machineMoneyFind = machineMoney.stream()
						.filter(mm -> mm.getMoneyBills().getId() == money.getId()).findFirst().get();

				if (machineMoneyFind.getQuantity() > 0) {
					if (machineMoneyFind.getQuantity() < quantity) {
						quantity = machineMoneyFind.getQuantity();
					}

					machineMoneyFind.setQuantity(machineMoneyFind.getQuantity() - quantity);
					machineMoneyToSave.add(machineMoneyFind);
				} else {
					quantity = 0;
				}
			}

			_value = _value - (money.getValue() * quantity);
			withdrawList.add(CurrentAccountWithdrawFactory.Create(money.getLabel(), quantity));

			// Case needs values only > 0
//			if (_value == 0) {
//				break;
//			}
		}

		if (_value > 0) {
			throw new NotAcceptableException("Caixa temporariamente indisponível");
		}

		machine.setBalance(machine.getBalance() - form.value);
		machineRepository.save(machine);

		machineMoneyRepository.saveAll(machineMoneyToSave);
		withdrawRepository.save(WithdrawFactory.Create(machine, form.value));

		return withdrawList;
	}

	public MachineDto deposit(UUID machineId, UUID moneyBillsId, CurrentAccountDepositForm form)
			throws ExceptionHandler {
		Machine machine = machineRepository.findById(machineId)
				.orElseThrow(() -> new NotFoundException("Máquina não encontrado"));

		MoneyBills moneyBills = moneyBillsRepository.findById(moneyBillsId)
				.orElseThrow(() -> new NotFoundException("Nota não encontrado"));

		int newMachineBalance = (form.quantity * moneyBills.getValue()) + machine.getBalance();
		machine.setBalance(newMachineBalance);

		MachineMoneyBills machineMoneyFind = machine.getMoneyBills().stream()
				.filter(mm -> mm.getMoneyBills().getId() == moneyBills.getId()).findFirst().get();

		int newMachineQuantity = machineMoneyFind.getQuantity() + form.quantity;
		machineMoneyFind.setQuantity(newMachineQuantity);

		machineRepository.save(machine);
		machineMoneyRepository.save(machineMoneyFind);

		return MachineFactory.Create(machine);
	}

}
