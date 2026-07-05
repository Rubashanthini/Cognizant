package com.library.application;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

/**
 * SpringCoreMainApp - Exercises 1, 2, 3, 5, 7, 8.
 *
 * Loads the plain Spring IoC container from applicationContext.xml
 * (src/main/resources/applicationContext.xml) which:
 *   - Defines the bookRepository and bookService beans explicitly in XML
 *     (Exercises 1 and 5).
 *   - Wires bookRepository into bookService using BOTH constructor
 *     injection and setter injection (Exercises 2 and 7).
 *   - Registers the LoggingAspect bean and enables AspectJ auto-proxying,
 *     so every call made below to bookService is intercepted with Before,
 *     After and execution-time (Around) logging (Exercises 3 and 8).
 *
 * Run this class directly from your IDE, or with:
 *   mvn exec:java -Dexec.mainClass="com.library.application.SpringCoreMainApp"
 */
public class SpringCoreMainApp {

    public static void main(String[] args) {

        // Load the Spring IoC container from the XML configuration file.
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the bookService bean - Spring has already injected its
        // BookRepository dependency (via constructor AND setter, see
        // applicationContext.xml).
        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("\n--- Dependency Injection check ---");
        System.out.println("BookRepository successfully injected into BookService: "
                + (bookService.getBookRepository() != null));

        System.out.println("\n--- Listing all books (repository pre-loaded with sample data) ---");
        Collection<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("\n--- Adding a new book ---");
        Book newBook = new Book("Head First Design Patterns", "Eric Freeman", "978-0596007126", 42.50, 2004);
        bookService.addBook(newBook);
        System.out.println("Total books now: " + bookService.getBookCount());

        System.out.println("\n--- Fetching a book by id ---");
        Book fetched = bookService.getBookById(1L);
        System.out.println("Book with id=1 -> " + fetched);

        // Close the context so the AOP execution-time logs for shutdown
        // related calls are also flushed cleanly.
        ((ClassPathXmlApplicationContext) context).close();
    }
}
