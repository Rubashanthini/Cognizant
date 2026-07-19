-- ============================================================
-- Sample data for Employee Management System
-- ============================================================

-- Departments
INSERT INTO department (id, name, created_date, last_modified_date, created_by, last_modified_by) VALUES
    (1, 'Engineering', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (2, 'Human Resources', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (3, 'Finance', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (4, 'Marketing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- Employees
INSERT INTO employee (id, name, email, department_id, created_date, last_modified_date, created_by, last_modified_by) VALUES
    (1, 'Alice Johnson',  'alice.johnson@cognizant.com',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (2, 'Bob Smith',      'bob.smith@cognizant.com',      1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (3, 'Carol Williams', 'carol.williams@cognizant.com', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (4, 'David Brown',    'david.brown@cognizant.com',    3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (5, 'Emma Davis',     'emma.davis@cognizant.com',     3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (6, 'Frank Miller',   'frank.miller@cognizant.com',   4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (7, 'Grace Wilson',   'grace.wilson@cognizant.com',   4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (8, 'Henry Moore',    'henry.moore@cognizant.com',    1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (9, 'Ivy Taylor',     'ivy.taylor@cognizant.com',     2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
    (10, 'Jack Anderson', 'jack.anderson@cognizant.com',  NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- Reset the sequence generators so ids continue from 11 / 5 for new inserts via the app
ALTER TABLE employee ALTER COLUMN id RESTART WITH 11;
ALTER TABLE department ALTER COLUMN id RESTART WITH 5;
