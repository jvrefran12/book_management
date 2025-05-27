package com.library.bookmanager.service;

import com.library.bookmanager.entity.Book;
import com.library.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
            bookRepository.findById(id).ifPresent(book -> {
                book.setIsDeleted(true);
                bookRepository.save(book);
            });
    }

    public Book restoreBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setIsDeleted(false);
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public List<Book> searchBooks(Book book) {
        boolean hasTitle = book.getTitle() != null && !book.getTitle().isBlank();
        boolean hasAuthor = book.getAuthor() != null && !book.getAuthor().isBlank();
        boolean hasDesc = book.getDescription() != null && !book.getDescription().isBlank();

        if (hasTitle && hasAuthor && hasDesc) {
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndDescriptionContainingIgnoreCase(
                    book.getTitle(), book.getAuthor(), book.getDescription()
            );
        } else if (hasTitle && hasAuthor) {
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(
                    book.getTitle(), book.getAuthor()
            );
        } else if (hasTitle) {
            return bookRepository.findByTitleContainingIgnoreCase(book.getTitle());
        } else if (hasAuthor) {
            return bookRepository.findByAuthorContainingIgnoreCase(book.getAuthor());
        } else if (hasDesc) {
            return bookRepository.findByDescriptionContainingIgnoreCase(book.getDescription());
        } else {
            return Collections.emptyList(); // Or throw an error if needed
        }

    }
}




