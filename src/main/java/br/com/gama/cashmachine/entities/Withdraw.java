package br.com.gama.cashmachine.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Withdraw extends SuperEntity {

	private int value;
	private LocalDateTime withdrawnAt;

	@ManyToOne
	@JoinColumn(name = "machine_id", nullable = false)
	private Machine machine;

	public Withdraw() {
		super();
	}

	public Withdraw(Machine machine, int value) {
		super();
		this.machine = machine;
		this.value = value;
		this.withdrawnAt = LocalDateTime.now();
	}

	public Withdraw(Machine machine, int value, LocalDateTime withdrawnAt) {
		super();
		this.machine = machine;
		this.value = value;
		this.withdrawnAt = withdrawnAt;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LocalDateTime getWithdrawnAt() {
		return withdrawnAt;
	}

	public void setWithdrawnAt(LocalDateTime withdrawnAt) {
		this.withdrawnAt = withdrawnAt;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}
