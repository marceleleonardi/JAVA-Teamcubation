package com.example.transacaoAPI.model;

import java.time.LocalDateTime;

public class Transacao {
	private double amount;
	private LocalDateTime timestamp;

	public Transacao() {}

	public Transacao(double amount, LocalDateTime timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
