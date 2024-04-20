package com.ws_biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.BookRequest;
import com.ws_biblioteca.api.service.BookService;


@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/find/{idLibro}")
    private ResponseEntity<Object> findBook(@PathVariable int idLibro) {
        try {
            Book bookFound = bookService.findBook(idLibro);
            if(bookFound == null) {
                return new ResponseEntity<>(bookFound, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(bookFound);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    private ResponseEntity<Object> registerBook(@RequestBody BookRequest bookRequest) {
        try {
            String bookRegistered = bookService.createBook(bookRequest);
            return ResponseEntity.ok(bookRegistered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{idLibro}")
    private ResponseEntity<Object> modifyBook(@PathVariable int idLibro, @RequestBody BookRequest bookRequest) {
        try {
            String bookRegistered = bookService.editBook(bookRequest, idLibro);
            if(bookRegistered == null) {
                return new ResponseEntity<>(bookRegistered, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(bookRegistered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/delete/{idLibro}")
    private ResponseEntity<Object> deleteBook(@PathVariable int idLibro) {
        try {
            String bookDeleted = bookService.deleteBook(idLibro);
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

    @GetMapping("/listPreDev")
    private ResponseEntity<Object> listPreDev() {
        try {
            return ResponseEntity.ok(bookService.listPreDev());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
