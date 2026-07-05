package com.library.repository;

import com.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * BookRepository - Exercises 1, 2, 3, 5, 6, 7, 8.
 *
 * This is a plain, in-memory "repository" class (backed by a Map) that is
 * used to demonstrate:
 *   - Exercise 1: Bean definition in applicationContext.xml.
 *   - Exercise 2: Being injected (DI/IoC) into BookService.
 *   - Exercise 5: XML based bean configuration.
 *   - Exercise 6: Annotation based configuration (@Repository).
 *   - Exercise 7: Being supplied through both constructor and setter injection.
 *
 * NOTE ON DESIGN: Exercise 9 separately asks for a Spring Data JPA
 * "BookRepository interface" backed by H2. Because Java does not allow two
 * different types with the same simple name in the same package, the
 * Spring Data JPA repository used for Exercise 9 has been named
 * {@link BookJpaRepository} instead, so that both requirements can coexist
 * in the same project without a naming collision. See BookJpaRepository
 * and the README for details.
 *
 * The @Repository annotation below satisfies Exercise 6's annotation
 * requirement. It has no effect unless component-scanning is enabled
 * (see applicationContext-annotation.xml); the plain applicationContext.xml
 * used by Exercises 1/2/3/5/7/8 wires this bean explicitly via <bean>.
 */
@Repository("bookRepository")
public class BookRepository {

    /** In-memory data store simulating a database table. */
    private final Map<Long, Book> bookStore = new LinkedHashMap<>();

    /** Generates unique ids for new books. */
    private final AtomicLong idGenerator = new AtomicLong();

    /**
     * Default constructor. Pre-loads some sample data (requirement #14)
     * so the Spring Core demos have something to display immediately.
     */
    public BookRepository() {
        save(new Book("Effective Java", "Joshua Bloch", "978-0134685991", 45.99, 2018));
        save(new Book("Clean Code", "Robert C. Martin", "978-0132350884", 39.99, 2008));
        save(new Book("Spring in Action", "Craig Walls", "978-1617294945", 49.99, 2018));
    }

    /**
     * Saves a new book or updates an existing one.
     *
     * @param book the book to save
     * @return the saved book (with an id assigned if it was new)
     */
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.incrementAndGet());
        }
        bookStore.put(book.getId(), book);
        return book;
    }

    /**
     * Finds a book by its id.
     *
     * @param id the book id
     * @return the matching book, or null if not found
     */
    public Book findById(Long id) {
        return bookStore.get(id);
    }

    /**
     * Returns all books currently stored.
     *
     * @return collection of all books
     */
    public Collection<Book> findAll() {
        return bookStore.values();
    }

    /**
     * Deletes a book by its id.
     *
     * @param id the book id
     */
    public void deleteById(Long id) {
        bookStore.remove(id);
    }

    /**
     * Returns the total number of books currently stored.
     *
     * @return the book count
     */
    public int count() {
        return bookStore.size();
    }
}
