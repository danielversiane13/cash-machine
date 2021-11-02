package br.com.gama.cashmachine.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gama.cashmachine.entities.MachineMoneyBills;

@Repository
public interface MachineMoneyBillsRepository extends JpaRepository<MachineMoneyBills, UUID> {
	//
}
