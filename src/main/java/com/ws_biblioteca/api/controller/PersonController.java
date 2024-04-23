package com.ws_biblioteca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.Person;
import com.ws_biblioteca.api.service.PersonService;

@Controller
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping("find/{idPersona}")
    private ResponseEntity<Object> findPerson(@PathVariable String idPersona) {
        try {
            Person personFound = personService.findPerson(idPersona);
            return ResponseEntity.ok(personFound);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    private ResponseEntity<Object> listPeople() {
        try {
            List<Person> personList = personService.listPeople();
            return ResponseEntity.ok(personList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/{idLibro}/BorrowBook")
    private ResponseEntity<Object> borrowBook(@RequestBody Person persona, @PathVariable int idLibro) {
        try {
            System.out.println(persona);
            String bookBorrowed = personService.bookBorrowed(persona, idLibro);
            return ResponseEntity.ok(bookBorrowed);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/returnBooks")
    private ResponseEntity<Object> returnBooks(@RequestParam int idLibro) {
        try {
            String bookReturned = personService.returnBook(idLibro);
            return ResponseEntity.ok(bookReturned);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{cedula}/listBorrowed")
    private ResponseEntity<Object> listBorrowed(@PathVariable String cedula) {
        try{
            List<Book> bookList = personService.listBorrowed(cedula);
            return ResponseEntity.ok(bookList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
