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
import com.ws_biblioteca.api.model.PreDev;
import com.ws_biblioteca.api.mapper.BookMapper;
import com.ws_biblioteca.api.mapper.PreDevMapper;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unchecked")
    public Book findBook(int idLibro) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_LIBRO")
                    .declareParameters(new SqlParameter("@idLibro", Types.INTEGER))
                    .returningResultSet("registro", new BookMapper());

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idLibro", idLibro);

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);
            List<Book> resultSet = (List<Book>) returnedResultSet.get("registro");

            if (resultSet.size() > 0) {
                System.out.println("Libro consultado");
                return resultSet.get(0);
            } else {
                System.out.println("Libro no encontrado");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String registerBook(Book book) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_CU_LIBRO")
                    .declareParameters(
                            new SqlParameter("@idLibro", Types.INTEGER),
                            new SqlParameter("@titulo", Types.NVARCHAR),
                            new SqlParameter("@autor", Types.NVARCHAR),
                            new SqlParameter("@anioEdicion", Types.NCHAR),
                            new SqlParameter("@genero", Types.NVARCHAR),
                            new SqlOutParameter("@resultado", Types.NVARCHAR),
                            new SqlOutParameter("@msgError", Types.NVARCHAR));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idLibro", book.getIdLibro())
                    .addValue("@titulo", book.getTitulo())
                    .addValue("@autor", book.getAutor())
                    .addValue("@anioEdicion", book.getAnioEdicion())
                    .addValue("@genero", book.getGenero());

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);

            if(returnedResultSet.get("@msgError") != null)
                System.out.println("Error: " + returnedResultSet.get("@msgError"));
            
            String resultado = (String) returnedResultSet.get("@resultado");
            System.out.println("Resultado: " + resultado);
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteBook(int idLibro) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_U_LIBRO_DAR_BAJA")
                    .declareParameters(
                            new SqlParameter("@idLibro", Types.INTEGER),
                            new SqlOutParameter("@resultado", Types.NVARCHAR),
                            new SqlOutParameter("@msgError", Types.NVARCHAR));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idLibro", idLibro);

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);

            if(returnedResultSet.get("@msgError") != null)
                System.out.println("Error: " + returnedResultSet.get("@msgError"));
            
            String resultado = (String) returnedResultSet.get("@resultado");
            System.out.println("Resultado: " + resultado);
            return resultado;
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

            System.out.println("Lista consultada");
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PreDev> listPreDev() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_PRE_DEV")
                    .returningResultSet("registro", new PreDevMapper());

            Map<String, Object> returnedResultSet = jdbcCall.execute(new MapSqlParameterSource());
            List<PreDev> resultSet = (List<PreDev>) returnedResultSet.get("registro");

            System.out.println("Lista PreDev consultada");
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}