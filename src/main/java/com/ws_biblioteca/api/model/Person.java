package com.ws_biblioteca.api.model;

import lombok.Data;

@Data
public class Person {
    String cedula;
    String nombre;
    String apellido;

    public Person(String nombre, String apellido, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }
}
