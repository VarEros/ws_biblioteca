package com.ws_biblioteca.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws_biblioteca.api.model.Book;

@Component
public class BookMapper implements RowMapper<Book>{
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setIdBook(rs.getInt("id_libro"));
        book.setTitle(rs.getString("titulo"));
        book.setAuthor(rs.getString("autor"));
        book.setYearEdition(rs.getInt("anio_edicion"));
        book.setGenre(rs.getString("genero"));
        book.setCode(rs.getString("codigo"));
        book.setStatus(rs.getInt("estado"));        
        book.setDate_borrow(getLocalDateTime(rs.getTimestamp("fecha_prestado")));
        book.setIdPerson(rs.getInt("id_persona"));
        return book;
    }

    private LocalDateTime getLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toLocalDateTime();
    }
}
