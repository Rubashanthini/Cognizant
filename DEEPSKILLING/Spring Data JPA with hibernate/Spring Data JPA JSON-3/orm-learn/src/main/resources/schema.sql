-- ==========================================================
-- HANDS-ON 1,2,4,5,6 SCHEMA :: Employee / Department / Skill
-- ==========================================================

DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS product;

DROP TABLE IF EXISTS attempt_option;
DROP TABLE IF EXISTS attempt_question;
DROP TABLE IF EXISTS `option`;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS attempt;
DROP TABLE IF EXISTS user;

CREATE TABLE department (
    dept_id     INT AUTO_INCREMENT PRIMARY KEY,
    dept_name   VARCHAR(100) NOT NULL,
    location    VARCHAR(100)
);

CREATE TABLE skill (
    skill_id    INT AUTO_INCREMENT PRIMARY KEY,
    skill_name  VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    emp_id      INT AUTO_INCREMENT PRIMARY KEY,
    emp_name    VARCHAR(100) NOT NULL,
    salary      DOUBLE NOT NULL,
    permanent   BOOLEAN NOT NULL DEFAULT FALSE,
    dept_id     INT,
    CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

CREATE TABLE employee_skill (
    emp_id      INT NOT NULL,
    skill_id    INT NOT NULL,
    PRIMARY KEY (emp_id, skill_id),
    CONSTRAINT fk_es_employee FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    CONSTRAINT fk_es_skill FOREIGN KEY (skill_id) REFERENCES skill(skill_id)
);

-- ==========================================================
-- HANDS-ON 6 SCHEMA :: Product (Criteria API dynamic search)
-- ==========================================================

CREATE TABLE product (
    product_id   INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(150) NOT NULL,
    ram          INT,
    cpu          VARCHAR(100),
    weight       DOUBLE,
    os           VARCHAR(100),
    rating       DOUBLE,
    price        DOUBLE
);

-- ==========================================================
-- HANDS-ON 3 SCHEMA :: Quiz Application
-- ==========================================================

CREATE TABLE user (
    user_id     INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL,
    password    VARCHAR(200) NOT NULL
);

CREATE TABLE question (
    question_id     INT AUTO_INCREMENT PRIMARY KEY,
    question_text   VARCHAR(500) NOT NULL
);

CREATE TABLE `option` (
    option_id       INT AUTO_INCREMENT PRIMARY KEY,
    option_text     VARCHAR(300) NOT NULL,
    is_correct      BOOLEAN NOT NULL DEFAULT FALSE,
    question_id     INT NOT NULL,
    CONSTRAINT fk_option_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE attempt (
    attempt_id      INT AUTO_INCREMENT PRIMARY KEY,
    attempt_date    DATETIME NOT NULL,
    score            DOUBLE,
    user_id          INT NOT NULL,
    CONSTRAINT fk_attempt_user FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE attempt_question (
    attempt_question_id INT AUTO_INCREMENT PRIMARY KEY,
    attempt_id           INT NOT NULL,
    question_id          INT NOT NULL,
    CONSTRAINT fk_aq_attempt FOREIGN KEY (attempt_id) REFERENCES attempt(attempt_id),
    CONSTRAINT fk_aq_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE attempt_option (
    attempt_option_id     INT AUTO_INCREMENT PRIMARY KEY,
    attempt_question_id   INT NOT NULL,
    option_id             INT NOT NULL,
    CONSTRAINT fk_ao_attempt_question FOREIGN KEY (attempt_question_id) REFERENCES attempt_question(attempt_question_id),
    CONSTRAINT fk_ao_option FOREIGN KEY (option_id) REFERENCES `option`(option_id)
);
