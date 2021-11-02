package br.com.gama.cashmachine.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class MoneyBills extends SuperEntity {

	private String label;
	private int value;

	@OneToMany(mappedBy = "moneyBills")
	Set<MachineMoneyBills> machineMoney;

	public MoneyBills() {
		super();
	}

	public MoneyBills(String label, int value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

}
