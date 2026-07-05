-- ============================================================
-- Hands-on 1: Country table creation
-- co_code varchar(2) primary key, co_name varchar(50)
-- ============================================================
CREATE TABLE IF NOT EXISTS country (
    co_code VARCHAR(2)  NOT NULL,
    co_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (co_code)
);
