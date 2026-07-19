package com.cognizant.employeesystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Exercise 7: Enabling Entity Auditing.
 *
 * A @MappedSuperclass that provides createdDate / lastModifiedDate /
 * createdBy / lastModifiedBy fields to any entity that extends it.
 * The {@link AuditingEntityListener} intercepts persist/update lifecycle
 * events and populates these fields automatically, working together with
 * {@code @EnableJpaAuditing} (see config.JpaAuditingConfig) and the
 * {@link com.cognizant.employeesystem.config.AuditorAwareImpl} bean.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
