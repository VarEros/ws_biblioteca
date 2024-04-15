package com.ws_biblioteca.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.Person;
import com.ws_biblioteca.api.repository.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    public String bookBorrowed(Person person, int idLibro) {
        return personRepository.borrowBook(person, idLibro);
    }

    public List<Book> listBorrowed(String cedula) {
        return personRepository.listBorrowed(cedula);
    }
}
