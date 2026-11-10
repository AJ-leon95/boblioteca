package com.jhonatan.biblioteca.repository;

import com.jhonatan.biblioteca.model.Autor;
import com.jhonatan.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(Autor autor);
    List<Libro> findByAutorNombre (String nombre);
}
