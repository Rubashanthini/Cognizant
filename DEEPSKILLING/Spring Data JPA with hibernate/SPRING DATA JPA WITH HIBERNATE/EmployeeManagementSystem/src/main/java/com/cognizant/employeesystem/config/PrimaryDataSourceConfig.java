package com.cognizant.employeesystem.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 9: Customizing Data Source Configuration / Externalized
 * Configuration / Demonstrating Multiple Data Sources.
 *
 * This configuration class takes explicit control of the PRIMARY data
 * source (values still externalized via the standard
 * {@code spring.datasource.*} properties, see application.properties)
 * instead of relying purely on Spring Boot's implicit auto-configuration.
 * It is marked {@code @Primary} so it wins whenever more than one
 * DataSource / EntityManagerFactory / PlatformTransactionManager bean is
 * present in the context - which is the case here because of the
 * secondary "audit" data source declared in
 * {@link SecondaryDataSourceConfig}.
 *
 * Repositories under com.cognizant.employeesystem.repository (Employee,
 * Department) are bound to THIS persistence unit.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.cognizant.employeesystem.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class PrimaryDataSourceConfig {

    /**
     * Built from the externalized spring.datasource.* properties.
     * Spring Boot's DataSourceBuilder auto-detects the H2 driver on the
     * classpath and wires it up - this is "leveraging Spring Boot
     * auto-configuration" while still allowing full customization here.
     */
    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        // Exercise 10: Hibernate batch processing performance settings
        properties.put("hibernate.jdbc.batch_size", "20");
        properties.put("hibernate.order_inserts", "true");
        properties.put("hibernate.order_updates", "true");

        return builder
                .dataSource(dataSource)
                .packages("com.cognizant.employeesystem.entity")
                .persistenceUnit("primary")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
