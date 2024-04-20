package com.ws_biblioteca.api.model;

import lombok.Data;

@Data
public class LateFee {
    private int idMulta;
    private int monto;
    private int idLibro;
    private String codigo;
    private String titulo;
    private int idPersona;
    private String cedula;
    private String nombre;
    private String apellido;
}
