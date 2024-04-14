package com.ws_biblioteca.api.model;

import java.security.Timestamp;

import lombok.Data;

@Data
public class Book {
    private int idBook;
    private String title;
    private String author;
    private int yearEdition;
    private String genre;
    private int status;
    private Timestamp date_borrow;
    private int idPerson;

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
