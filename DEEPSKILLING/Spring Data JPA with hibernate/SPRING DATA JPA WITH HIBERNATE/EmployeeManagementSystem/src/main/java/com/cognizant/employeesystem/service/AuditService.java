package com.cognizant.employeesystem.service;

import com.cognizant.employeesystem.audit.entity.AuditLog;
import com.cognizant.employeesystem.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Exercise 9: Service that writes records into the SECONDARY data source
 * ("auditdb") every time an Employee or Department is created, updated,
 * or deleted - demonstrating a real use case for managing multiple data
 * sources within one application.
 *
 * {@code @Transactional("secondaryTransactionManager")} pins this
 * method's transaction to the secondary persistence unit configured in
 * {@link com.cognizant.employeesystem.config.SecondaryDataSourceConfig},
 * completely independent of the primary Employee/Department transaction.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Transactional("secondaryTransactionManager")
    public void recordAction(String entityName, Long entityId, String action, String performedBy) {
        AuditLog auditLog = AuditLog.builder()
                .entityName(entityName)
                .entityId(entityId)
                .action(action)
                .performedBy(performedBy)
                .performedAt(LocalDateTime.now())
                .build();
        auditLogRepository.save(auditLog);
        log.debug("Recorded audit entry: {} {} on {}#{}", action, performedBy, entityName, entityId);
    }
}
