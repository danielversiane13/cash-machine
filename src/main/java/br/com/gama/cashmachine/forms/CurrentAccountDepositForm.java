package br.com.gama.cashmachine.forms;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class CurrentAccountDepositForm {

	@Column(name = "quantity", nullable = false)
	@NotNull
	@Range(min = 1)
	public Integer quantity;

}
