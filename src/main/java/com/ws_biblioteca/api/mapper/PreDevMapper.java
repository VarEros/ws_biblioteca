package com.ws_biblioteca.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ws_biblioteca.api.model.PreDev;

@Component
public class PreDevMapper implements RowMapper<PreDev>{
    @Override
    public PreDev mapRow(ResultSet rs, int rowNum) throws SQLException {
        PreDev preDev = new PreDev();
        preDev.setIdLibro(rs.getInt("id_libro"));
        preDev.setCodigo(rs.getString("codigo"));
        preDev.setTitulo(rs.getString("titulo"));
        preDev.setIdPersona(rs.getInt("id_persona"));
        preDev.setCedula(rs.getString("cedula"));
        preDev.setNombre(rs.getString("nombre"));
        preDev.setApellido(rs.getString("apellido"));
        preDev.setFecha(getLocalDateTime(rs.getTimestamp("fecha")));
        preDev.setTipo(rs.getBoolean("tipo"));
        return preDev;
    }

    private LocalDateTime getLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toLocalDateTime();
    }
}

