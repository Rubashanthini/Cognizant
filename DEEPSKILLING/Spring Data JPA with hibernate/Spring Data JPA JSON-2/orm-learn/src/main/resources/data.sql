-- ==========================================================
-- HANDS-ON 1: Country data
-- ==========================================================
INSERT INTO country (country_name) VALUES
('India'),
('Indonesia'),
('Iceland'),
('Ireland'),
('Italy'),
('United States'),
('United Kingdom'),
('Australia'),
('Austria'),
('Argentina');

-- ==========================================================
-- HANDS-ON 2: Stock data
-- ==========================================================
-- Facebook - September 2019
INSERT INTO stock (stock_symbol, trade_date, open_price, high_price, low_price, close_price, volume) VALUES
('FB', '2019-09-03', 185.00, 187.50, 184.20, 186.20, 12500000),
('FB', '2019-09-10', 188.00, 190.10, 186.50, 189.40, 13500000),
('FB', '2019-09-17', 190.50, 193.20, 189.00, 191.75, 11800000),
('FB', '2019-09-24', 187.30, 189.00, 185.40, 186.90, 15200000);

-- Google - closing price > 1250
INSERT INTO stock (stock_symbol, trade_date, open_price, high_price, low_price, close_price, volume) VALUES
('GOOGL', '2019-08-01', 1205.00, 1215.00, 1198.00, 1210.50, 1500000),
('GOOGL', '2019-08-08', 1220.00, 1260.00, 1215.00, 1255.75, 1750000),
('GOOGL', '2019-08-15', 1258.00, 1290.00, 1250.00, 1275.30, 1900000),
('GOOGL', '2019-08-22', 1280.00, 1310.00, 1265.00, 1305.60, 2100000);

-- Records for highest transaction volumes (top 3)
INSERT INTO stock (stock_symbol, trade_date, open_price, high_price, low_price, close_price, volume) VALUES
('AAPL', '2019-09-05', 210.00, 214.00, 208.50, 212.30, 30500000),
('MSFT', '2019-09-06', 137.00, 139.50, 136.20, 138.90, 28700000),
('AMZN', '2019-09-09', 1830.00, 1855.00, 1820.00, 1840.50, 26200000);

-- Netflix stocks - for lowest three closing prices
INSERT INTO stock (stock_symbol, trade_date, open_price, high_price, low_price, close_price, volume) VALUES
('NFLX', '2019-09-04', 285.00, 290.00, 275.00, 279.60, 8500000),
('NFLX', '2019-09-11', 275.00, 280.00, 265.00, 268.30, 9100000),
('NFLX', '2019-09-18', 270.00, 274.00, 258.00, 261.75, 8800000),
('NFLX', '2019-09-25', 290.00, 296.00, 288.00, 292.10, 7600000);

-- ==========================================================
-- HANDS-ON 3/4/5/6: Department, Employee, Skill data
-- ==========================================================
INSERT INTO department (dept_name) VALUES
('Engineering'),
('Human Resources'),
('Finance');

INSERT INTO employee (emp_name, salary, dept_id) VALUES
('Alice Johnson', 75000.00, 1),
('Bob Smith', 65000.00, 1),
('Carla Diaz', 58000.00, 2),
('David Lee', 72000.00, 3),
('Emma Watson', 68000.00, 1);

INSERT INTO skill (skill_name) VALUES
('Java'),
('Spring Boot'),
('SQL'),
('Communication'),
('Excel');

-- Many-to-Many mapping between employee and skill
INSERT INTO employee_skill (emp_id, skill_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(3, 4),
(4, 5),
(4, 3),
(5, 1),
(5, 2);
