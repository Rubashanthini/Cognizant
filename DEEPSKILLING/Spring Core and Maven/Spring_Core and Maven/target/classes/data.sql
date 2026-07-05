-- =====================================================================
-- Exercise 9 / requirement #14: sample data loaded into H2 on startup.
-- =====================================================================

INSERT INTO book (title, author, isbn, price, published_year) VALUES
    ('Effective Java', 'Joshua Bloch', '978-0134685991', 45.99, 2018),
    ('Clean Code', 'Robert C. Martin', '978-0132350884', 39.99, 2008),
    ('Spring in Action', 'Craig Walls', '978-1617294945', 49.99, 2018),
    ('Head First Design Patterns', 'Eric Freeman', '978-0596007126', 42.50, 2004);
