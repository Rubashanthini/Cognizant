package com.cognizant.employeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Data Transfer Object used for Employee REST request/response payloads.
 * Decouples the JPA entity from the API contract and carries only the
 * department's id on write operations (department object on read).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Employee name must not be blank")
    private String name;

    @NotBlank(message = "Employee email must not be blank")
    @Email(message = "Employee email must be a valid email address")
    private String email;

    /** Id of the department this employee belongs to (may be null). */
    private Long departmentId;

    /** Department name - populated only on read responses for convenience. */
    private String departmentName;
}
