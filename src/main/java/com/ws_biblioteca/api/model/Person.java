package com.ws_biblioteca.api.model;

import lombok.Data;

@Data
public class Person {
    String cedula;
    String names;
    String lastnames;

    public Person(String names, String lastnames, String cedula) {
        this.names = names;
        this.lastnames = lastnames;
        this.cedula = cedula;
    }
}
