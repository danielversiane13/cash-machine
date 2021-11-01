package br.com.gama.cashmachine.factories;

import br.com.gama.cashmachine.dto.MachineDto;
import br.com.gama.cashmachine.entities.Machine;
import br.com.gama.cashmachine.forms.MachineForm;

public class MachineFactory {

	public static Machine Create(MachineForm form) {
		return new Machine(form.name, form.balance);
	}

	public static MachineDto Create(Machine machine) {
		var dto = new MachineDto();

		dto.id = machine.getId();
		dto.name = machine.getName();
		dto.balance = machine.getBalance();

		return dto;
	}

}
