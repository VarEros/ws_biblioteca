package com.ws_biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.service.BookService;


@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    private ResponseEntity<Object> registerBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre, @RequestParam int yearEdition) {
        try {
            Book bookObj = new Book(title, author, yearEdition, genre);
            String bookRegistered = bookService.createEditBook(bookObj);
            return ResponseEntity.ok(bookRegistered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/edit/{idBook}")
    private ResponseEntity<Object> modifyBook(@PathVariable int idBook, @RequestParam String title, @RequestParam String author, @RequestParam String genre, @RequestParam int yearEdition) {
        try {
            Book bookObj = new Book(idBook,title, author, yearEdition, genre);
            String bookRegistered = bookService.createEditBook(bookObj);
            if(bookRegistered == null) {
                return new ResponseEntity<>(bookRegistered, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(bookRegistered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/delete/{idBook}")
    private ResponseEntity<Object> deleteBook(@PathVariable int idBook) {
        try {
            String bookDeleted = bookService.deleteBook(idBook);
            if(bookDeleted == null) {
                return new ResponseEntity<>(bookDeleted, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(bookDeleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    private ResponseEntity<Object> listBooks() {
        try {
            return ResponseEntity.ok(bookService.listBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
