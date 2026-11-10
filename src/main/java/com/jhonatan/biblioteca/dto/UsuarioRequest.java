package com.jhonatan.biblioteca.dto;

import com.jhonatan.biblioteca.exception.Cedula;
import com.jhonatan.biblioteca.model.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank(message = "El userName es necesario")
    @Size(min = 3,max = 25,message = "EL usuario deve estar en el rango de caracteres entre 3-25")
    private String userName;
    @NotBlank(message = "El password es necesario")
    @Size(min = 6,max = 25,message = "EL password deve estar en el rango de caracteres entre 6-25")
    private String password;
    @Cedula(message = "La cédula ecuatoriana no es válida")
    private String cedula;
    @NotNull(message = "El rol es necesario y debe ser Admin/Usuario")
    private Rol rolUsu;

    public UsuarioRequest() {
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
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

    public Rol getRolUsu() {
        return rolUsu;
    }

    public void setRolUsu(Rol rolUsu) {
        this.rolUsu = rolUsu;
    }
}
