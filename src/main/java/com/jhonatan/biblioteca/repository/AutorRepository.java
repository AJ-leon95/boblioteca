package com.jhonatan.biblioteca.repository;

import com.jhonatan.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre (String nombre);
}
