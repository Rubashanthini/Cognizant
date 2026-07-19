package com.cognizant.employeesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Exercise 7: Enables Spring Data JPA auditing for the whole application
 * (primary persistence unit). Once enabled, entities extending
 * {@link com.cognizant.employeesystem.entity.Auditable} automatically get
 * their createdDate / lastModifiedDate / createdBy / lastModifiedBy
 * fields populated by the {@code AuditingEntityListener}.
 *
 * auditorAwareRef points Spring to the bean that supplies "who" performed
 * the change (see {@link AuditorAwareImpl}).
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
