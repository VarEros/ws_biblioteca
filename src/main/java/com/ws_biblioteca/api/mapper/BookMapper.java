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
        book.setIdLibro(rs.getInt("id_libro"));
        book.setTitulo(rs.getString("titulo"));
        book.setAutor(rs.getString("autor"));
        book.setAnioEdicion(rs.getString("anio_edicion"));
        book.setGenero(rs.getString("genero"));
        book.setCodigo(rs.getString("codigo"));
        book.setEstado(rs.getInt("estado"));        
        book.setFechaPrestado(getLocalDateTime(rs.getTimestamp("fecha_prestado")));
        book.setIdPersona(rs.getInt("id_persona"));
        return book;
    }

    private LocalDateTime getLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toLocalDateTime();
    }
}
