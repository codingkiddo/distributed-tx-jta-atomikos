package com.codingkiddo.dtx.audit;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "audit_log")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fromAcc;

	@Column(nullable = false)
	private String toAcc;

	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false)
	private Instant ts = Instant.now();

	public AuditLog() {
	}

	public AuditLog(String fromAcc, String toAcc, BigDecimal amount) {
		this.fromAcc = fromAcc;
		this.toAcc = toAcc;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public String getFromAcc() {
		return fromAcc;
	}

	public String getToAcc() {
		return toAcc;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Instant getTs() {
		return ts;
	}
}
