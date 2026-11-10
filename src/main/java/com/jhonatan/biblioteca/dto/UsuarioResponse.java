package com.jhonatan.biblioteca.dto;

import java.time.LocalDate;

public class UsuarioResponse {
    private Long id;
    private String userName;
    private String password;
    private String rolUsu;
    private String cedula;
    private LocalDate fechaNacimento;
    private Integer edad;

    public UsuarioResponse() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaNacimento() {
        return fechaNacimento;
    }

    public void setFechaNacimento(LocalDate fechaNacimento) {
        this.fechaNacimento = fechaNacimento;
    }

    public String getRolUsu() {
        return rolUsu;
    }

    public void setRolUsu(String rol) {
        this.rolUsu = rol;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
