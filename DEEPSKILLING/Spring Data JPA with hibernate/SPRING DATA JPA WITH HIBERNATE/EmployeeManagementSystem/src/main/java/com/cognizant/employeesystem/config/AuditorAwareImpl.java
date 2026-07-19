package com.cognizant.employeesystem.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Exercise 7: Supplies the "current auditor" (the user responsible for
 * a create/update operation) to Spring Data JPA's auditing infrastructure.
 *
 * In a production application this would typically read the
 * authenticated principal from Spring Security's SecurityContextHolder,
 * e.g.:
 * <pre>
 *   Authentication authentication =
 *       SecurityContextHolder.getContext().getAuthentication();
 *   return Optional.ofNullable(authentication)
 *       .filter(Authentication::isAuthenticated)
 *       .map(Authentication::getName);
 * </pre>
 *
 * Since this exercise project does not include Spring Security, a fixed
 * system user is returned so that @CreatedBy / @LastModifiedBy columns
 * are still demonstrably populated end-to-end.
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    private static final String DEFAULT_AUDITOR = "api-user";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(DEFAULT_AUDITOR);
    }
}
