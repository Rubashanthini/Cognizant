-- ==========================================================
-- HANDS-ON 1: Country table
-- ==========================================================
DROP TABLE IF EXISTS country;
CREATE TABLE country (
    country_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL
);

-- ==========================================================
-- HANDS-ON 2: Stock table
-- ==========================================================
DROP TABLE IF EXISTS stock;
CREATE TABLE stock (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    stock_symbol VARCHAR(20)   NOT NULL,
    trade_date   DATE          NOT NULL,
    open_price   DOUBLE        NOT NULL,
    high_price   DOUBLE        NOT NULL,
    low_price    DOUBLE        NOT NULL,
    close_price  DOUBLE        NOT NULL,
    volume       BIGINT        NOT NULL
);

-- ==========================================================
-- HANDS-ON 3/4/5/6: Department, Employee, Skill, Employee_Skill
-- ==========================================================
DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS skill;

CREATE TABLE department (
    dept_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    emp_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    salary   DOUBLE       NOT NULL,
    dept_id  BIGINT,
    CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES department (dept_id)
);

CREATE TABLE skill (
    skill_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(100) NOT NULL
);

CREATE TABLE employee_skill (
    emp_id   BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    PRIMARY KEY (emp_id, skill_id),
    CONSTRAINT fk_es_employee FOREIGN KEY (emp_id) REFERENCES employee (emp_id),
    CONSTRAINT fk_es_skill FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);
