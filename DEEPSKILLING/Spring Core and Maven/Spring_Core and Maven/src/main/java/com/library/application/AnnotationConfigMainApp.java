package com.library.application;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AnnotationConfigMainApp - Exercise 6.
 *
 * Loads the Spring IoC container from applicationContext-annotation.xml,
 * which enables component-scanning of the com.library package instead of
 * declaring explicit &lt;bean&gt; elements. BookService is discovered via
 * @Service, BookRepository via @Repository, and LoggingAspect via @Aspect
 * + @Component - all automatically registered and wired together.
 *
 * Run this class directly from your IDE, or with:
 *   mvn exec:java -Dexec.mainClass="com.library.application.AnnotationConfigMainApp"
 */
public class AnnotationConfigMainApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-annotation.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("\n--- Exercise 6: Annotation-based configuration check ---");
        System.out.println("BookRepository injected via component scanning: "
                + (bookService.getBookRepository() != null));
        System.out.println("Books currently in repository: " + bookService.getBookCount());
        bookService.getAllBooks().forEach(System.out::println);

        ((ClassPathXmlApplicationContext) context).close();
    }
}
