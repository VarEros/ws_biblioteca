package com.ws_biblioteca.api.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PreDev {
    private int idLibro;
    private String codigo;
    private String titulo;
    private int idPersona;
    private String cedula;
    private String nombre;
    private String apellido;
    private LocalDateTime fecha;
    private boolean tipo;
}
