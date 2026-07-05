package com.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Book entity.
 *
 * This single class is reused throughout the whole project:
 *  - Exercises 1, 2, 3, 5, 6, 7, 8 use it as a simple POJO / model class
 *    that is stored in the in-memory {@link com.library.repository.BookRepository}.
 *  - Exercise 9 uses it as a JPA entity (annotated with {@code @Entity}) that is
 *    persisted to the H2 database through {@link com.library.repository.BookJpaRepository}.
 *
 * Having JPA annotations on the class does not affect the plain Spring Core
 * demonstrations (Exercises 1-8) at all, because those parts of the
 * application never start a JPA / persistence context.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private double price;

    private int publishedYear;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Book() {
    }

    /**
     * Convenience constructor used for creating sample data.
     */
    public Book(String title, String author, String isbn, double price, int publishedYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.publishedYear = publishedYear;
    }

    /**
     * Full constructor including id, useful for updates.
     */
    public Book(Long id, String title, String author, String isbn, double price, int publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.publishedYear = publishedYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", publishedYear=" + publishedYear +
                '}';
    }
}
