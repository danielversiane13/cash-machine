package br.com.gama.cashmachine.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gama.cashmachine.entities.Withdraw;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, UUID> {
	public Page<Withdraw> findByMachineId(Pageable pageable, UUID id);
}
