-- =====================================================================
-- Exercise 9: explicit schema for the H2 in-memory database.
-- spring.jpa.hibernate.ddl-auto=none means Hibernate will NOT generate
-- this schema automatically - it is created from this file instead.
-- =====================================================================

DROP TABLE IF EXISTS book;

CREATE TABLE book (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    author         VARCHAR(255) NOT NULL,
    isbn           VARCHAR(50),
    price          DOUBLE,
    published_year INT
);
