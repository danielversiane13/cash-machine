package br.com.gama.cashmachine.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MachineMoneyBills {

	@Id
	UUID id;

	@ManyToOne
	@JoinColumn(name = "machine_id")
	Machine machine;

	@ManyToOne
	@JoinColumn(name = "money_bills_id")
	MoneyBills moneyBills;

	int quantity;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

	public MachineMoneyBills() {
		super();
		this.id = UUID.randomUUID();
		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	public MachineMoneyBills(Machine machine, MoneyBills moneyBills, int quantity) {
		super();

		this.id = UUID.randomUUID();
		this.machine = machine;
		this.moneyBills = moneyBills;
		this.quantity = quantity;

		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	public UUID getId() {
		return id;
	}

	public MoneyBills getMoneyBills() {
		return moneyBills;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
