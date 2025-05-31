package com.library.bookmanager.dto;

import com.library.bookmanager.entity.Book;
import lombok.Getter;

import java.time.LocalDate;

@Getter

public class dataFormat {
    // Book fields (view data)
    private Long id;
    private String title;
    private String author;
    private String description;
    private LocalDate publishedDate;
    private Boolean isDeleted;
    private LocalDate deletedAt;
    private String isAvailable; // raw boolean still stored

    // Constructor for mapping a Book entity to this DTO
    public dataFormat(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.publishedDate = book.getPublishedDate();
        this.author = book.getAuthor();
        this.deletedAt = book.getDeletedAt();
        this.isDeleted = book.getIsDeleted();
        this.isAvailable = Boolean.TRUE.equals(book.getIsAvailable()) ? "Yes" : "No";
    }


}
