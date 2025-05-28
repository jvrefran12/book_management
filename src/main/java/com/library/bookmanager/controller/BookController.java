package com.library.bookmanager.controller;

import com.library.bookmanager.entity.Book;
import com.library.bookmanager.service.BookService;
import com.library.bookmanager.dto.NewBooksListRequest;
import com.library.bookmanager.dto.IdListRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Book> createMultipleBooks(@RequestBody NewBooksListRequest request) {
        return bookService.addMultipleBook(request.getNewBooks());
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/ids")
    public List<Book> getMultipleBooks(@RequestBody IdListRequest request) {
        return bookService.getMultipleBooks(request.getIds());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("ids")
    public ResponseEntity<Void> deleteMultipleBooks(@RequestBody IdListRequest request) {
        bookService.deleteMultipleBook(request.getIds());
        return  ResponseEntity.noContent().build();
    }

}
