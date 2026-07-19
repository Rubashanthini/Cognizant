package com.cognizant.employeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Data Transfer Object used for Department REST request/response payloads.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department name must not be blank")
    private String name;

    /** Names of employees belonging to this department (read-only, optional). */
    private List<String> employeeNames;
}
