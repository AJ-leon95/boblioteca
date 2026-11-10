package com.jhonatan.biblioteca.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usu")
    private Long id;
    @Column(name = "username_usu",unique = true,nullable = false)
    private String username;
    @Column(name = "password_usu")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_usu")
    private Rol rolUsu;
    @Column(name = "cedula_usu",unique = true, nullable = false)
    private String cedula;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "edad")
    private Integer edad;

    public Usuario() {
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Usuario(Integer edad) {
        this.edad = edad;
    }
}
