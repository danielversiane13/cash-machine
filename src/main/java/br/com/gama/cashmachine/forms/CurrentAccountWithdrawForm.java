package br.com.gama.cashmachine.forms;

import org.hibernate.validator.constraints.Range;

public class CurrentAccountWithdrawForm {

	@Range(min = 1)
	public int value;

}
