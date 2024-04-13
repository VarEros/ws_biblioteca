package com.ws_biblioteca.api.repository;

import java.sql.Types;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

import com.ws_biblioteca.api.model.User;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String iniciarSesion(User user) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PR_R_INICIO_SESION")
                .declareParameters(
                        new SqlParameter("@usuario", Types.NVARCHAR),
                        new SqlParameter("@password", Types.NVARCHAR),
                        new SqlOutParameter("@resultado", Types.NVARCHAR));

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("@usuario", user.getUser())
                .addValue("@password", user.getPassword());

        Map<String, Object> returnedResultSet = jdbcCall.execute(paramMap);
        System.out.println(returnedResultSet.get("@resultado"));
        return (String) returnedResultSet.get("@resultado");
    }
}

