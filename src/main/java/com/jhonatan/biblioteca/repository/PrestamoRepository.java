package com.jhonatan.biblioteca.repository;

import com.jhonatan.biblioteca.model.Libro;
import com.jhonatan.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Long> {
    List<Prestamo> findByLibroTitulo(String titulo);
    Optional<Prestamo> findByNombrePersona (String nombre);
}
