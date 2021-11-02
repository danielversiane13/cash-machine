package br.com.gama.cashmachine.factories;

import br.com.gama.cashmachine.dto.MoneyBillsDto;
import br.com.gama.cashmachine.entities.MoneyBills;

public class MoneyBillsFactory {

	public static MoneyBillsDto Create(MoneyBills moneyBills) {
		var dto = new MoneyBillsDto();

		dto.id = moneyBills.getId();
		dto.label = moneyBills.getLabel();

		return dto;
	}

}
