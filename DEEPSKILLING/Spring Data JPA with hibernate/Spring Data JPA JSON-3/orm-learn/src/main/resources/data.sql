-- ==========================================================
-- Department
-- ==========================================================
INSERT INTO department (dept_name, location) VALUES
('Engineering', 'Chennai'),
('Human Resources', 'Bangalore'),
('Finance', 'Mumbai'),
('Sales', 'Delhi');

-- ==========================================================
-- Skill
-- ==========================================================
INSERT INTO skill (skill_name) VALUES
('Java'),
('Spring Boot'),
('Hibernate'),
('SQL'),
('React'),
('AWS');

-- ==========================================================
-- Employee
-- ==========================================================
INSERT INTO employee (emp_name, salary, permanent, dept_id) VALUES
('Arun Kumar',      75000.00, TRUE,  1),
('Bhavna Rao',      65000.00, TRUE,  1),
('Chitra Iyer',     55000.00, FALSE, 2),
('Deepak Sharma',   95000.00, TRUE,  1),
('Esha Nair',       48000.00, FALSE, 3),
('Farhan Ali',      72000.00, TRUE,  4),
('Gayatri Menon',   61000.00, FALSE, 2),
('Harish Chandran', 88000.00, TRUE,  1);

-- ==========================================================
-- Employee-Skill mapping (many-to-many)
-- ==========================================================
INSERT INTO employee_skill (emp_id, skill_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 4),
(3, 5),
(4, 1), (4, 2), (4, 3), (4, 6),
(5, 4),
(6, 5), (6, 6),
(7, 4), (7, 5),
(8, 1), (8, 2), (8, 6);

-- ==========================================================
-- Product (Hands-on 6 - Criteria API)
-- ==========================================================
INSERT INTO product (product_name, ram, cpu, weight, os, rating, price) VALUES
('Dell Inspiron 15',   8,  'Intel i5',   1.8, 'Windows 11', 4.2,  55999.00),
('MacBook Air M2',     16, 'Apple M2',   1.24,'macOS',      4.8,  114900.00),
('HP Pavilion 14',     8,  'Intel i3',   1.5, 'Windows 11', 3.9,  42999.00),
('Lenovo Legion 5',    16, 'AMD Ryzen 7',2.3, 'Windows 11', 4.5,  84999.00),
('Asus ROG Strix',     32, 'Intel i9',   2.5, 'Windows 11', 4.7,  159999.00),
('Acer Aspire 7',      8,  'AMD Ryzen 5',1.9, 'Linux',      4.0,  48999.00),
('Dell XPS 13',        16, 'Intel i7',   1.2, 'Windows 11', 4.6,  99999.00);

-- ==========================================================
-- Quiz Application - User
-- ==========================================================
INSERT INTO user (username, email, password) VALUES
('rahul.dev',   'rahul.dev@example.com',   'pass123'),
('priya.k',     'priya.k@example.com',     'pass456'),
('sanjay.m',    'sanjay.m@example.com',    'pass789');

-- ==========================================================
-- Quiz Application - Question
-- ==========================================================
INSERT INTO question (question_text) VALUES
('What does JPA stand for?'),
('Which annotation marks a class as a JPA entity?'),
('Which method executes a native SQL query in Spring Data JPA?');

-- ==========================================================
-- Quiz Application - Option
-- ==========================================================
INSERT INTO `option` (option_text, is_correct, question_id) VALUES
('Java Persistence API', TRUE, 1),
('Java Programming API', FALSE, 1),
('Java Platform Architecture', FALSE, 1),
('@Entity', TRUE, 2),
('@Table', FALSE, 2),
('@Component', FALSE, 2),
('@Query(nativeQuery = true)', TRUE, 3),
('@NativeOnly', FALSE, 3),
('@RawQuery', FALSE, 3);

-- ==========================================================
-- Quiz Application - Attempt
-- ==========================================================
INSERT INTO attempt (attempt_date, score, user_id) VALUES
('2026-07-01 10:15:00', 66.67, 1),
('2026-07-02 11:30:00', 100.00, 2);

-- ==========================================================
-- Quiz Application - AttemptQuestion
-- ==========================================================
INSERT INTO attempt_question (attempt_id, question_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2);

-- ==========================================================
-- Quiz Application - AttemptOption (option chosen by the user for each attempted question)
-- ==========================================================
INSERT INTO attempt_option (attempt_question_id, option_id) VALUES
(1, 1),  -- attempt 1 -> question 1 -> chose correct option
(2, 4),  -- attempt 1 -> question 2 -> chose correct option
(3, 8),  -- attempt 1 -> question 3 -> chose wrong option
(4, 1),  -- attempt 2 -> question 1 -> chose correct option
(5, 4);  -- attempt 2 -> question 2 -> chose correct option
