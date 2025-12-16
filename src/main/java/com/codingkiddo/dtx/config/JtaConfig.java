package com.codingkiddo.dtx.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import jakarta.transaction.UserTransaction;
import org.postgresql.xa.PGXADataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@Configuration
public class JtaConfig {

    @Bean(initMethod = "init", destroyMethod = "close", name = "ds1")
    public DataSource dataSource1() {
        PGXADataSource xa = new PGXADataSource();
        xa.setUrl(System.getProperty("DB1_URL", System.getenv().getOrDefault("DB1_URL", "jdbc:postgresql://localhost:5433/app")));
        xa.setUser(System.getProperty("DB1_USER", System.getenv().getOrDefault("DB1_USER", "app")));
        xa.setPassword(System.getProperty("DB1_PASSWORD", System.getenv().getOrDefault("DB1_PASSWORD", "app")));
        return atomikosXa("db1", xa);
    }

    @Bean(initMethod = "init", destroyMethod = "close", name = "ds2")
    public DataSource dataSource2() {
        PGXADataSource xa = new PGXADataSource();
        xa.setUrl(System.getProperty("DB2_URL", System.getenv().getOrDefault("DB2_URL", "jdbc:postgresql://localhost:5434/audit")));
        xa.setUser(System.getProperty("DB2_USER", System.getenv().getOrDefault("DB2_USER", "audit")));
        xa.setPassword(System.getProperty("DB2_PASSWORD", System.getenv().getOrDefault("DB2_PASSWORD", "audit")));
        return atomikosXa("db2", xa);
    }

    private DataSource atomikosXa(String uniqueName, XADataSource xa) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName(uniqueName);
        ds.setXaDataSource(xa);
        ds.setMinPoolSize(1);
        ds.setMaxPoolSize(10);
        ds.setBorrowConnectionTimeout(30);
        ds.setTestQuery("SELECT 1");
        return ds;
    }

    @Bean
    public JtaTransactionManager transactionManager() throws Exception {
        UserTransaction userTx = new UserTransactionImp();
        UserTransactionManager utm = new UserTransactionManager();
        utm.setForceShutdown(false);
        return new JtaTransactionManager(userTx, utm);
    }
}
