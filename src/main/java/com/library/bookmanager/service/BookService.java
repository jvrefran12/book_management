package com.library.bookmanager.service;

import com.library.bookmanager.dto.dataFormat;
import com.library.bookmanager.entity.Book;
import com.library.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    LocalDate today = LocalDate.now();

    //Create books
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    //Create Multiple books
    public List<Book> addMultipleBook(List<Book> newBooks) {
        return bookRepository.saveAll(newBooks);
    }

    //Get all books
    public List<dataFormat> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .sorted(Comparator.comparing(Book::getId))
                .map(dataFormat::new)
                .filter(book -> !Boolean.TRUE.equals(book.getIsDeleted()))
                .collect(Collectors.toList());
    }

    //Get multiple specific books
    public List<dataFormat> getMultipleBooks(List<Long> ids) {
        List<Book> books = bookRepository.findAllById(ids);
        return books.stream()
                .sorted(Comparator.comparing(Book::getId))
                .map(dataFormat::new)
                .filter(book -> !Boolean.TRUE.equals(book.getIsDeleted()))
                .collect(Collectors.toList());
   }
    //Get specific books
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id)
                .filter(book -> !Boolean.TRUE.equals(book.getIsDeleted()));
    }

    //Move Books to archive
    public void deleteBook(Long id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setIsDeleted(true);
            book.setDeletedAt(LocalDate.now());
            bookRepository.save(book);
        });
    }

    //Move multiple Books to archive
    public void deleteMultipleBook(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return;

        List<Book> books = bookRepository.findAllById(ids);
        books.forEach(book -> {
            book.setIsDeleted(true);
            book.setDeletedAt(today);
        });
        bookRepository.saveAll(books);
    }

    //archive Books
    public void archiveBook(Long id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setIsDeleted(false);
            book.setDeletedAt(null);
            bookRepository.save(book);
        });
    }

    //archive multiple Books
    public void archiveMultipleBook(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return;
        List<Book> books = bookRepository.findAllById(ids);
        books.forEach(book -> {
            book.setIsDeleted(true);
            book.setDeletedAt(null);
        });
        bookRepository.saveAll(books);

    }

}
