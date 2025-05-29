package com.library.bookmanager.dto;

import lombok.Setter;
import lombok.Getter;

import com.library.bookmanager.entity.Book;

import java.util.List;

@Getter
@Setter
public class NewBooksListRequest {
    private List<Book> newBooks;
}
