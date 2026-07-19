-- =========================================================
-- SpringJUnitTesting - schema.sql
-- Creates the USERS table used by the User entity.
-- =========================================================

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
