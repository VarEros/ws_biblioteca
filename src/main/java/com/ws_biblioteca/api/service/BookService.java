package com.ws_biblioteca.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public String createEditBook(Book book) {
        String result = bookRepository.registerBook(book);
        return result;
    }

    public String deleteBook(int idLibro) {
        String result = bookRepository.deleteBook(idLibro);
        return result;
    } 

    public List<Book> listBooks() {
        List<Book> result = bookRepository.listBooks();
        return result;
    }
}
