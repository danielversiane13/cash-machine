package br.com.gama.cashmachine.forms;

import org.hibernate.validator.constraints.Range;

public class CurrentAccountDepositForm {

	@Range(min = 1, max = 100000)
	public Integer quantity;

}
