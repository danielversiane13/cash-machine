package br.com.gama.cashmachine.entities;

import javax.persistence.Entity;

@Entity
public class MoneyBills extends SuperEntity {

	private String label;
	private int value;

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
