package com.library.controller;

import com.library.entity.Book;
import com.library.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * BookController - Exercise 9.
 *
 * REST controller exposing full CRUD operations over the Book entity,
 * backed by Spring Data JPA and the H2 in-memory database.
 *
 * Endpoints:
 *   GET    /books        -> list all books
 *   GET    /books/{id}   -> get a single book by id
 *   POST   /books        -> create a new book
 *   PUT    /books/{id}   -> update an existing book
 *   DELETE /books/{id}   -> delete a book
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookJpaRepository bookJpaRepository;

    /**
     * Constructor injection of the Spring Data JPA repository.
     *
     * @param bookJpaRepository the JPA repository for Book
     */
    @Autowired
    public BookController(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    /**
     * GET /books - returns every book in the database.
     *
     * @return list of all books
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookJpaRepository.findAll();
    }

    /**
     * GET /books/{id} - returns a single book by its id.
     *
     * @param id the book id
     * @return 200 with the book if found, otherwise 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookJpaRepository.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /books - creates a new book.
     *
     * @param book the book to create (in the request body)
     * @return 201 with the created book, including its generated id
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookJpaRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * PUT /books/{id} - updates an existing book.
     *
     * @param id          the id of the book to update
     * @param bookDetails the new details for the book
     * @return 200 with the updated book if it existed, otherwise 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> existingBook = bookJpaRepository.findById(id);
        if (!existingBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Book book = existingBook.get();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        book.setPublishedYear(bookDetails.getPublishedYear());
        Book updated = bookJpaRepository.save(book);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /books/{id} - deletes a book by its id.
     *
     * @param id the id of the book to delete
     * @return 204 if deleted, or 404 if the book did not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookJpaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
