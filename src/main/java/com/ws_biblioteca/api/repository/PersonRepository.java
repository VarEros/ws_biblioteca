package com.ws_biblioteca.api.repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ws_biblioteca.api.mapper.BookMapper;
import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.Person;

@Repository
public class PersonRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public String borrowBook(Person person, int idLibro) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_U_LIBRO_PRESTADO")
                    .declareParameters(
                            new SqlParameter("@nombres", Types.NVARCHAR),
                            new SqlParameter("@apellidos", Types.NVARCHAR),
                            new SqlParameter("@cedula", Types.NVARCHAR),
                            new SqlParameter("@idLibro", Types.INTEGER));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@nombres", person.getNames())
                    .addValue("@apellidos", person.getLastnames())
                    .addValue("@cedula", person.getCedula())
                    .addValue("@idLibro", idLibro);

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);

            if(returnedResultSet.get("@msgError") != null)
                System.out.println("Error: " + returnedResultSet.get("@msgError"));
            
                return (String) returnedResultSet.get("@resultado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> listBorrowed(String cedula) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_LIBROS_POR_DEVOLVER")
                    .declareParameters(new SqlParameter("@cedula", Types.NVARCHAR))
                    .returningResultSet("registro", new BookMapper());

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@cedula", cedula);
            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);
            List<Book> resultSet = (List<Book>) returnedResultSet.get("registro");
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
