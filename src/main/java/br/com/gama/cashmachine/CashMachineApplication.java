package br.com.gama.cashmachine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.entities.MachineMoneyBills;
import br.com.gama.cashmachine.entities.MoneyBills;
import br.com.gama.cashmachine.repositories.MachineMoneyBillsRepository;
import br.com.gama.cashmachine.repositories.MachineRepository;
import br.com.gama.cashmachine.repositories.MoneyBillsRepository;

@SpringBootApplication
public class CashMachineApplication implements CommandLineRunner {

	@Autowired
	private MachineRepository machineRepository;

	@Autowired
	private MoneyBillsRepository moneyBillsRepository;

	@Autowired
	private MachineMoneyBillsRepository machineMoneyBillsRepository;

	public static void main(String[] args) {
		SpringApplication.run(CashMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int amountMoneyBills = 5;

		List<Machine> machines = new ArrayList<Machine>(List.of(new Machine("Machine 01"), new Machine("Machine 02")));
		machineRepository.saveAll(machines);

		List<MoneyBills> moneyBills = moneyBillsRepository.findAll();

		machines.stream().forEach(machine -> {
			moneyBills.stream().forEach(money -> {
				machineMoneyBillsRepository.save(new MachineMoneyBills(machine, money, amountMoneyBills));
				int newBalance = machine.getBalance() + money.getValue() * amountMoneyBills;
				machine.setBalance(newBalance);
			});
			machineRepository.save(machine);
		});
	}

}
