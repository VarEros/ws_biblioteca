package com.ws_biblioteca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.Person;
import com.ws_biblioteca.api.service.PersonService;

@Controller
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping("{cedula}/BorrowBook")
    private ResponseEntity<Object> borrowBook(@RequestParam String names, @RequestParam String lastnames, @PathVariable String cedula, @RequestParam int idBook) {
        try {
            Person personObj = new Person(names, lastnames, cedula);
            String bookBorrowed = personService.bookBorrowed(personObj, idBook);
            return ResponseEntity.ok(bookBorrowed);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/returnBooks")
    private ResponseEntity<Object> returnBooks(@RequestParam int idBook1, @RequestParam int idBook2, @RequestParam int idBook3) {
        try {
            String bookReturned = personService.returnBooks(idBook1, idBook2, idBook3);
            return ResponseEntity.ok(bookReturned);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("{cedula}/listBorrowed")
    private ResponseEntity<Object> listBorrowed(@PathVariable String cedula) {
        try{
            List<Book> bookList = personService.listBorrowed(cedula);
            return ResponseEntity.ok(bookList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}