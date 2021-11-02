package br.com.gama.cashmachine.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MachineForm {

	@NotNull
	@NotBlank
	@NotEmpty
	public String name;

}
