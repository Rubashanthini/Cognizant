package com.cognizant.employeesystem.audit.repository;

import com.cognizant.employeesystem.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Exercise 9: Repository backed by the SECONDARY data source
 * ("auditdb"). Wired through
 * {@link com.cognizant.employeesystem.config.SecondaryDataSourceConfig}
 * rather than the default/primary JPA repository infrastructure.
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByEntityNameOrderByPerformedAtDesc(String entityName);
}
