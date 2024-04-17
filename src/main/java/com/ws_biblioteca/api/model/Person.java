package com.ws_biblioteca.api.model;

import lombok.Data;

@Data
public class Person {
    String cedula;
    String nombres;
    String apellidos;

    public Person(String nombres, String apellidos, String cedula) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
    }
}
