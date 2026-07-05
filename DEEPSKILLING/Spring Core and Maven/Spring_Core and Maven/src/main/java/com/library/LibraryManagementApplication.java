package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LibraryManagementApplication - Exercise 9.
 *
 * Main entry point for the Spring Boot part of the project. Starting this
 * class boots an embedded web server, initialises Spring Data JPA against
 * the H2 in-memory database (loading schema.sql / data.sql), and exposes
 * the REST CRUD endpoints implemented in {@link com.library.controller.BookController}.
 *
 * Run with:
 *   mvn spring-boot:run
 * or:
 *   java -jar target/LibraryManagement.jar
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
        System.out.println("=================================================");
        System.out.println(" LibraryManagement Spring Boot application started");
        System.out.println(" Try: GET http://localhost:8080/books");
        System.out.println(" H2 console: http://localhost:8080/h2-console");
        System.out.println("=================================================");
    }
}
