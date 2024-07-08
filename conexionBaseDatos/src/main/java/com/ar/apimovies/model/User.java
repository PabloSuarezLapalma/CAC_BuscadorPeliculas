package com.ar.apimovies.model;

import java.util.Date;

public class User {
    private Integer id;
    private String nombre;
    private String email;
    private String apellido;
    private String password;
    private Date fechaNacimiento;
    private String pais;

    public User () {
    }
    public User ( String nombre, String email, String apellido, String password, Date fechaNacimiento, String pais) {
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getApellido() {
        return this.apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    public String getPais() {
        return this.pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }


}
