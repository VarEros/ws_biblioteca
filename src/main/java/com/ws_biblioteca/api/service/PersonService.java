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

    public String returnBooks(int idLibro1, int idLibro2, int idLibro3) {
        return personRepository.returnBooks(idLibro1, idLibro2, idLibro3);
    }

    public List<Book> listBorrowed(String cedula) {
        return personRepository.listBorrowed(cedula);
    }
}
