package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * BookService - Exercises 1, 2, 5, 6, 7.
 *
 * Business/service layer that depends on {@link BookRepository}.
 *
 * Demonstrates:
 *   - Exercise 1: declared as a bean in applicationContext.xml.
 *   - Exercise 2: Setter Injection of BookRepository (setBookRepository).
 *   - Exercise 5: XML bean configuration (constructor-arg / property).
 *   - Exercise 6: Annotation based configuration (@Service).
 *   - Exercise 7: BOTH constructor injection (via the parameterised
 *     constructor) and setter injection (via setBookRepository) are
 *     supported and can each be configured from applicationContext.xml.
 */
@Service("bookService")
public class BookService {

    /** The repository this service depends on (injected by Spring). */
    private BookRepository bookRepository;

    /**
     * Default no-argument constructor, required so that Spring can also
     * build this bean using pure setter injection (Exercise 2).
     */
    public BookService() {
    }

    /**
     * Constructor used for Constructor Injection (Exercise 7).
     *
     * @param bookRepository the repository dependency
     */
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Setter used for Setter Injection (Exercise 2 and Exercise 7).
     *
     * @param bookRepository the repository dependency
     */
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    /**
     * Adds a new book through the repository.
     *
     * @param book the book to add
     * @return the saved book
     */
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieves a book by id.
     *
     * @param id the book id
     * @return the matching book, or null if not found
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Retrieves all books currently known to the repository.
     *
     * @return collection of all books
     */
    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Deletes a book by id.
     *
     * @param id the book id
     */
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * Returns how many books are currently stored - used by the demo main
     * classes to prove the dependency injection succeeded.
     *
     * @return number of books
     */
    public int getBookCount() {
        return bookRepository.count();
    }
}
