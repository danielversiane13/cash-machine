package br.com.gama.cashmachine.entities;

import javax.persistence.Entity;

@Entity
public class Machine extends SuperEntity {

	private String name;
	private int balance;

	public Machine() {
		super();
	}

	public Machine(String name, int balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
