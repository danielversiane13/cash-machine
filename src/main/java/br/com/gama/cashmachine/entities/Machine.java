package br.com.gama.cashmachine.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Machine extends SuperEntity {

	private String name;
	private int balance;

	@OneToMany(mappedBy = "machine")
	Set<MachineMoneyBills> machineMoney;

	public Machine() {
		super();
	}

	public Machine(String name) {
		super();
		this.name = name;
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

	public Set<MachineMoneyBills> getMoneyBills() {
		return machineMoney;
	}

}
