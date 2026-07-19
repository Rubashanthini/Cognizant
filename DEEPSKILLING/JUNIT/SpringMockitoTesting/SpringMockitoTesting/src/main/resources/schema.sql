-- ============================================================
-- Schema definition for the "users" table
-- ============================================================
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id    BIGINT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);
