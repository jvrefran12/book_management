package com.library.bookmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity // Marks this class as a JPA entity
@Getter
@Setter
@Table(name = "books", schema = "books_schema") // Optional: Customize table name
public class Book {

    @Id // Marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    @Column(nullable = false) // Required field
    private String title;

    private String author;

    @Column(length = 1000)
    private String description;

    private LocalDate publishedDate;

    @JsonIgnore
    private Boolean isAvailable;

    @JsonProperty("availability")
    public String getAvailability() {
        return Boolean.TRUE.equals(isAvailable) ? "Yes" : "No";
    }

    private Boolean isDeleted = false; // 👈 soft delete flag

    //Constructor
    public Book(){}

    public Book(String title, String author, String description, LocalDate publishedDate, Boolean isAvailable, Boolean isDeleted) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedDate = publishedDate;
        this.isAvailable = isAvailable;
        this.isDeleted = isDeleted;
    }

}




