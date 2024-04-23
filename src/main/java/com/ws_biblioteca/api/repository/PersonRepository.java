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

import com.ws_biblioteca.api.mapper.BookMapper;
import com.ws_biblioteca.api.model.Book;
import com.ws_biblioteca.api.model.Person;

@Repository
public class PersonRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unchecked")
    public Person findPerson(String idPersona) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_PERSONA")
                    .declareParameters(new SqlParameter("@idPersona", Types.INTEGER))
                    .returningResultSet("registro", new BookMapper());

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idPersona", idPersona);

            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);
            List<Person> resultSet = (List<Person>) returnedResultSet.get("registro");

            if (resultSet.size() > 0) {
                System.out.println("Persona consultada");
                return (Person) resultSet.get(0);
            } else {
                System.out.println("Persona no encontrada");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Person> listPeople() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_PERSONAS")
                    .returningResultSet("registro", new BookMapper());

            Map<String, Object> returnedResultSet = jdbcCall.execute();
            List<Person> resultSet = (List<Person>) returnedResultSet.get("registro");

            System.out.println("Lista de personas consultada");
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String borrowBook(Person person, int idLibro) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_U_LIBRO_PRESTADO")
                    .declareParameters(
                            new SqlParameter("@nombre", Types.NVARCHAR),
                            new SqlParameter("@apellido", Types.NVARCHAR),
                            new SqlParameter("@cedula", Types.NCHAR),
                            new SqlParameter("@idLibro", Types.INTEGER),
                            new SqlOutParameter("@resultado", Types.NVARCHAR),
                            new SqlOutParameter("@msgError", Types.NVARCHAR));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@nombre", person.getNombre())
                    .addValue("@apellido", person.getApellido())
                    .addValue("@cedula", person.getCedula())
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

    public String returnBook(int idLibro) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_U_LIBRO_REGRESADO")
                    .declareParameters(
                            new SqlParameter("@idLibro", Types.INTEGER));

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
    public List<Book> listBorrowed(String cedula) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_LIBROS_POR_DEVOLVER")
                    .declareParameters(new SqlParameter("@cedula", Types.NCHAR))
                    .returningResultSet("registro", new BookMapper());

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@cedula", cedula);
            Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);
            List<Book> resultSet = (List<Book>) returnedResultSet.get("registro");

            System.out.println("Lista consultada por: " + cedula);
            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
