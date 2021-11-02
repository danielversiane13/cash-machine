package br.com.gama.cashmachine.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gama.cashmachine.entities.MoneyBills;

@Repository
public interface MoneyBillsRepository extends JpaRepository<MoneyBills, UUID> {
	public List<MoneyBills> findByActiveTrueOrderByValueDesc();
}
