package com.codingkiddo.dtx.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PersistenceConfig {
	@Bean(name = "emf1")
	public LocalContainerEntityManagerFactoryBean emf1(@Qualifier("ds1") DataSource ds, JpaProperties props) {
		return buildEmf(ds, "emf1", "com.codingkiddo.dtx.account", props);
	}

	@Bean(name = "emf2")
	public LocalContainerEntityManagerFactoryBean emf2(@Qualifier("ds2") DataSource ds, JpaProperties props) {
		return buildEmf(ds, "emf2", "com.codingkiddo.dtx.audit", props);
	}

	private LocalContainerEntityManagerFactoryBean buildEmf(DataSource ds, String unit, String packages,
			JpaProperties props) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(ds);
		emf.setPackagesToScan(packages);
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPersistenceUnitName(unit);
		Map<String, Object> jpaProps = new HashMap<>(props.getProperties());
		jpaProps.putIfAbsent("hibernate.transaction.jta.platform",
				"org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform");
		jpaProps.putIfAbsent("hibernate.transaction.coordinator_class", "jta");
		jpaProps.putIfAbsent("jakarta.persistence.transactionType", "JTA");
		jpaProps.putIfAbsent("hibernate.hbm2ddl.auto", "update");
		emf.setJpaPropertyMap(jpaProps);
		return emf;
	}
}
