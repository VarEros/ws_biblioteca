package com.ws_biblioteca.api.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Book {
    private int idLibro;
    private String titulo;
    private String autor;
    private int anioEdicion;
    private String genero;
    private String codigo;
    private int estado;
    private LocalDateTime fechaPrestado;
    private int idPersona;

    public Book() {
    }

    public Book(int idLibro, String titulo, String autor, int anioEdicion, String genero) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
        this.genero = genero;
    }

    public Book(String titulo, String autor, int anioEdicion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
        this.genero = genero;
    }
}
