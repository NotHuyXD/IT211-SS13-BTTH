package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exception.BookNotFoundException;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        log.debug("Request received to get all books");

        List<Book> books = bookRepository.findAll();

        log.info("Get all books successfully. Total books: {}", books.size());
        return books;
    }

    public Book getBookById(Long id) {
        log.debug("Request received to get book by id: {}", id);

        return bookRepository.findById(id)
                .map(book -> {
                    log.info("Get book successfully. Book id: {}", id);
                    return book;
                })
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", id);
                    return new BookNotFoundException(id);
                });
    }

    public Book createBook(Book book) {
        log.debug("Request received to create book: {}", book);

        Book savedBook = bookRepository.save(book);

        log.info("Create book successfully. Book id: {}", savedBook.getId());
        return savedBook;
    }

    public Book updateBook(Long id, Book bookRequest) {
        log.debug("Request received to update book. Id: {}, data: {}", id, bookRequest);

        Book existingBook = getBookById(id);
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setIsbn(bookRequest.getIsbn());
        existingBook.setPublicationYear(bookRequest.getPublicationYear());

        Book updatedBook = bookRepository.save(existingBook);

        log.info("Update book successfully. Book id: {}", id);
        return updatedBook;
    }

    public Book patchBook(Long id, Book bookRequest) {
        log.debug("Request received to patch book. Id: {}, data: {}", id, bookRequest);

        Book existingBook = getBookById(id);

        if (bookRequest.getTitle() != null) {
            existingBook.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getAuthor() != null) {
            existingBook.setAuthor(bookRequest.getAuthor());
        }
        if (bookRequest.getIsbn() != null) {
            existingBook.setIsbn(bookRequest.getIsbn());
        }
        if (bookRequest.getPublicationYear() != null) {
            existingBook.setPublicationYear(bookRequest.getPublicationYear());
        }

        Book patchedBook = bookRepository.save(existingBook);

        log.info("Patch book successfully. Book id: {}", id);
        return patchedBook;
    }

    public void deleteBook(Long id) {
        log.debug("Request received to delete book by id: {}", id);

        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);

        log.info("Delete book successfully. Book id: {}", id);
    }
}
