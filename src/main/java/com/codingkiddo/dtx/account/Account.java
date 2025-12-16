package com.codingkiddo.dtx.account;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String number;

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal balance = BigDecimal.ZERO;

	public Account() {
	}

	public Account(String number, BigDecimal balance) {
		this.number = number;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
