package br.com.gama.cashmachine.factories;

import br.com.gama.cashmachine.dto.CurrentAccountWithdrawDto;

public class CurrentAccountWithdrawFactory {

	public static CurrentAccountWithdrawDto Create(String moneyBills, int quantity) {
		var dto = new CurrentAccountWithdrawDto();

		dto.moneyBills = moneyBills;
		dto.quantity = quantity;

		return dto;
	}

}
