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

import com.ws_biblioteca.api.model.LateFee;
import com.ws_biblioteca.api.mapper.LateFeeMapper;

@Repository
public class LateFeeRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unchecked")
    public List<LateFee> listLateFees() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_R_MULTAS")
                    .returningResultSet("registro", new LateFeeMapper());

            Map<String, Object> returnedResultSet = jdbcCall.execute();
            List<LateFee> resultSet = (List<LateFee>) returnedResultSet.get("registro");

            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String payLateFee(int idMulta) {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("PR_D_MULTA_CANCELADA")
                    .declareParameters(
                            new SqlParameter("@idMulta", Types.INTEGER),
                            new SqlOutParameter("@mensaje", Types.NVARCHAR));

            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("@idMulta", idMulta);

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
}
