package com.ws_biblioteca.api.model;

import lombok.Data;

@Data
public class BookRequest {
    private String titulo;
    private String autor;
    private String genero;
    private int anioEdicion;
}
