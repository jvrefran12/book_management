package com.library.bookmanager.service;

import com.library.bookmanager.entity.Book;
import com.library.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> addMultipleBook(List<Book> newBooks) {
        return bookRepository.saveAll(newBooks);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //Get multiple specific books
    public List<Book> getMultipleBooks(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //Delete multiple Books
    public void deleteMultipleBook(List<Long> ids) { bookRepository.deleteAllById(ids); }

}
