package br.com.gama.cashmachine.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class WithdrawDto {

	public UUID id;
	public int value;
	public LocalDateTime withdrawnAt;

}
