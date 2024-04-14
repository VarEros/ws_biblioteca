package com.ws_biblioteca.api.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Book {
    private int idBook;
    private String title;
    private String author;
    private int yearEdition;
    private String genre;
    private String code;
    private int status;
    private LocalDateTime date_borrow;
    private int idPerson;

    public Book() {
    }

    public Book(int idBook, String title, String author, int yearEdition, String genre) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.yearEdition = yearEdition;
        this.genre = genre;
    }

    public Book(String title, String author, int yearEdition, String genre) {
        this.title = title;
        this.author = author;
        this.yearEdition = yearEdition;
        this.genre = genre;
    }
}
