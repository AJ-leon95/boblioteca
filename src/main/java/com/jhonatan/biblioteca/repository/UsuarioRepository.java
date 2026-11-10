package com.jhonatan.biblioteca.repository;

import com.jhonatan.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByCedula (String cedula);
    Optional<Usuario> findByUsername(String username);
    // metodo pa ver si existe el username en la bbdd
    boolean existsByUsername(String username);
    boolean existsByCedula(String cedula);
}
