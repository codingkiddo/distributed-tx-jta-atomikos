package com.codingkiddo.dtx;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codingkiddo.dtx.account.Account;
import com.codingkiddo.dtx.account.AccountRepository;

@Configuration
public class Bootstrap {

	@Bean
	CommandLineRunner seedAccounts(AccountRepository accountRepository) {
		return args -> {
			accountRepository.findByNumber("A-100")
					.orElseGet(() -> accountRepository.save(new Account("A-100", new BigDecimal("10000.00"))));

			accountRepository.findByNumber("A-200")
					.orElseGet(() -> accountRepository.save(new Account("A-200", new BigDecimal("5000.00"))));
		};
	}
}
