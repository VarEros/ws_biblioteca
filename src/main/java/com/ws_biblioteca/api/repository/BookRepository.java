package com.ws_biblioteca.api.repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ws_biblioteca.api.model.Book;

import com.ws_biblioteca.api.mapper.BookMapper;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String registerBook(Book book) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_CU_LIBRO")
                    .declareParameters(
                            new SqlParameter("@idLibro", Types.INTEGER),
                            new SqlParameter("@titulo", Types.NVARCHAR),
                            new SqlParameter("@autor", Types.NVARCHAR),
                            new SqlParameter("@anioEdicion", Types.INTEGER),
                            new SqlParameter("@genero", Types.NVARCHAR),
                            new SqlOutParameter("@resultado", Types.NVARCHAR),
                            new SqlOutParameter("@msgError", Types.NVARCHAR));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idLibro", book.getIdBook())
                    .addValue("@titulo", book.getTitle())
                    .addValue("@autor", book.getAuthor())
                    .addValue("@anioEdicion", book.getYearEdition())
                    .addValue("@genero", book.getGenre());

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);

            if(returnedResultSet.get("@msgError") != null)
                System.out.println("Error: " + returnedResultSet.get("@msgError"));
            
                return (String) returnedResultSet.get("@resultado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteBook(int idBook) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_U_LIBRO_DAR_BAJA")
                    .declareParameters(
                            new SqlParameter("@idLibro", Types.INTEGER),
                            new SqlOutParameter("@resultado", Types.NVARCHAR),
                            new SqlOutParameter("@msgError", Types.NVARCHAR));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idLibro", idBook);

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);

            if(returnedResultSet.get("@msgError") != null)
                System.out.println("Error: " + returnedResultSet.get("@msgError"));
            
                return (String) returnedResultSet.get("@resultado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_LIBROS")
                    .returningResultSet("registro", new BookMapper());

            Map<String, Object> returnedResultSet = jdbcCall.execute(new MapSqlParameterSource());
            List<Book> resultSet = (List<Book>) returnedResultSet.get("registro");
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
