package br.com.gama.cashmachine.factories;

import br.com.gama.cashmachine.dto.WithdrawDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.entities.Withdraw;

public class WithdrawFactory {

	public static Withdraw Create(Machine machine, int value) {
		return new Withdraw(machine, value);
	}

	public static WithdrawDto Create(Withdraw withdraw) {
		var dto = new WithdrawDto();

		dto.id = withdraw.getId();
		dto.value = withdraw.getValue();
		dto.withdrawnAt = withdraw.getWithdrawnAt();

		return dto;
	}

}
