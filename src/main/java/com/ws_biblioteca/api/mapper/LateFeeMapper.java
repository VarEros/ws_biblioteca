package com.ws_biblioteca.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws_biblioteca.api.model.LateFee;

@Component
public class LateFeeMapper implements RowMapper<LateFee>{
    @Override
    public LateFee mapRow(ResultSet rs, int rowNum) throws SQLException {
        LateFee lateFee = new LateFee();
        lateFee.setIdMulta(rs.getInt("id_multa"));
        lateFee.setMonto(rs.getInt("monto"));
        lateFee.setIdLibro(rs.getInt("id_libro"));
        lateFee.setCodigo(rs.getString("codigo"));
        lateFee.setTitulo(rs.getString("titulo"));
        lateFee.setIdPersona(rs.getInt("id_persona"));
        lateFee.setCedula(rs.getString("cedula"));
        lateFee.setNombre(rs.getString("nombre"));
        lateFee.setApellido(rs.getString("apellido"));

        return lateFee;
    }
}
