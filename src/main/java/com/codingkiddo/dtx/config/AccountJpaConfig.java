package com.codingkiddo.dtx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.codingkiddo.dtx.account", 
		entityManagerFactoryRef = "emf1", 
		transactionManagerRef = "transactionManager"
)
public class AccountJpaConfig {}
