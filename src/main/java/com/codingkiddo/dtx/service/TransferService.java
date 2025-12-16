package com.codingkiddo.dtx.service;

import com.codingkiddo.dtx.account.Account;
import com.codingkiddo.dtx.account.AccountRepository;
import com.codingkiddo.dtx.audit.AuditLog;
import com.codingkiddo.dtx.audit.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
	private final AccountRepository accounts;
	private final AuditLogRepository audit;

	public TransferService(AccountRepository accounts, AuditLogRepository audit) {
		this.accounts = accounts;
		this.audit = audit;
	}

	@Transactional
	public void transfer(String from, String to, BigDecimal amount) {
		Account a = accounts.findByNumber(from).orElseThrow(() -> new IllegalArgumentException("from not found"));
		Account b = accounts.findByNumber(to).orElseThrow(() -> new IllegalArgumentException("to not found"));
		if (a.getBalance().compareTo(amount) < 0)
			throw new IllegalStateException("insufficient funds");
		a.setBalance(a.getBalance().subtract(amount));
		b.setBalance(b.getBalance().add(amount));
		accounts.save(a);
		accounts.save(b);
		audit.save(new AuditLog(from, to, amount));

		// Uncomment to simulate a failure:
		// if (true) throw new RuntimeException("boom after both writes");
	}
}
