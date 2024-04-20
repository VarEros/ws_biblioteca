package com.ws_biblioteca.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.BookRequest;
import com.ws_biblioteca.api.model.PreDev;
import com.ws_biblioteca.api.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public String createBook(BookRequest bookRequest) {
        Book book = bookRequestToBook(bookRequest);
        String result = bookRepository.registerBook(book);
        return result;
    }

    public String editBook(BookRequest bookRequest, int idLibro) {
        Book book = bookRequestToBook(bookRequest);
        book.setIdLibro(idLibro);
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

    public List<PreDev> listPreDev() {
        List<PreDev> result = bookRepository.listPreDev();
        return result;
    }

    private Book bookRequestToBook (BookRequest bookRequest) {
        Book book = new Book(bookRequest.getTitulo(), bookRequest.getAutor(), bookRequest.getAnioEdicion(), bookRequest.getGenero());
        return book;
    }
}
