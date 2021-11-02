package br.com.gama.cashmachine.forms;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class CurrentAccountWithdrawForm {

	@Column(name = "value", nullable = false)
	@NotNull
	public Integer value;

}
