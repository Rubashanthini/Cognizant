package com.cognizant.employeesystem.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 9: Demonstrates managing a SECOND, completely independent
 * data source ("auditdb") inside the same Spring Boot application,
 * alongside the primary "testdb" configured in
 * {@link PrimaryDataSourceConfig}.
 *
 * Its own DataSource, LocalContainerEntityManagerFactoryBean and
 * PlatformTransactionManager beans are declared explicitly (none of
 * them are marked @Primary, so the primary beans always win when a
 * plain @Autowired DataSource/EntityManagerFactory is requested
 * elsewhere without a qualifier).
 *
 * Repositories under com.cognizant.employeesystem.audit.repository
 * (AuditLogRepository) are bound to THIS persistence unit.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.cognizant.employeesystem.audit.repository",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {

    /** Externalized via app.datasource.secondary.* in application.properties. */
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "app.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondaryDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        // No schema.sql for this DB - let Hibernate create the audit_log table itself.
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

        return builder
                .dataSource(dataSource)
                .packages("com.cognizant.employeesystem.audit.entity")
                .persistenceUnit("secondary")
                .properties(properties)
                .build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
