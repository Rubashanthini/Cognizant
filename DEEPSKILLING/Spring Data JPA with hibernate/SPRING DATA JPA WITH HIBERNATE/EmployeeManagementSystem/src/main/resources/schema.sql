-- ============================================================
-- Exercise 1 & 2: Schema definition for Employee Management System
-- H2 in-memory database
-- ============================================================

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

-- Department table (parent side of the one-to-many relationship)
CREATE TABLE department (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(100) NOT NULL UNIQUE,
    created_date        TIMESTAMP,
    last_modified_date  TIMESTAMP,
    created_by          VARCHAR(50),
    last_modified_by    VARCHAR(50)
);

-- Employee table (child side, holds FK to department)
CREATE TABLE employee (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    email               VARCHAR(150) NOT NULL UNIQUE,
    department_id       BIGINT,
    created_date        TIMESTAMP,
    last_modified_date  TIMESTAMP,
    created_by          VARCHAR(50),
    last_modified_by    VARCHAR(50),
    CONSTRAINT fk_employee_department FOREIGN KEY (department_id)
        REFERENCES department (id) ON DELETE SET NULL
);
