package com.jhonatan.biblioteca.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aut")
    private Long id;
    @Column(name = "nombre_aut")
    private String nombre;
    @Column(name = "nacionalidad_aut")
    private  String  nacionalidad;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libro = new ArrayList<>();
    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
