package com.cognizant.employeesystem.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Exercise 9: Entity that lives entirely in a SECOND, independent data
 * source ("auditdb") separate from the primary "testdb" that holds
 * Employee/Department. This demonstrates managing multiple data sources
 * within a single Spring Boot application - e.g. writing operational
 * audit trail records to a dedicated database.
 *
 * Persisted/queried through the "secondaryEntityManagerFactory" and
 * "secondaryTransactionManager" beans configured in
 * {@link com.cognizant.employeesystem.config.SecondaryDataSourceConfig}.
 */
@Entity
@Table(name = "audit_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the entity affected, e.g. "Employee" or "Department". */
    @Column(name = "entity_name", nullable = false, length = 50)
    private String entityName;

    /** Id of the affected record. */
    @Column(name = "entity_id")
    private Long entityId;

    /** CREATE, UPDATE, or DELETE. */
    @Column(name = "action", nullable = false, length = 20)
    private String action;

    @Column(name = "performed_by", length = 50)
    private String performedBy;

    @Column(name = "performed_at", nullable = false)
    private LocalDateTime performedAt;
}
