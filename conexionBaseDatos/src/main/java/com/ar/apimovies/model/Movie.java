package com.ar.apimovies.model;

import java.util.Date;

public class Movie {
    private Integer id;
    private String titulo;
    private Integer duracion;
    private String genero;
    private String imagen;
    public Movie() {
    }
    public Movie(String titulo, Integer duracion, String genero, String imagen) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.imagen = imagen;
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return this.titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getDuracion() {
        return this.duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public String getGenero() {
        return this.genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getImagen() {
        return this.imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
