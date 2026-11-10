package com.jhonatan.biblioteca.controller;

import com.jhonatan.biblioteca.dto.PrestamoRequest;
import com.jhonatan.biblioteca.dto.PrestamoResponse;
import com.jhonatan.biblioteca.model.Libro;
import com.jhonatan.biblioteca.model.Prestamo;
import com.jhonatan.biblioteca.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public PrestamoResponse guardar(@Valid @RequestBody PrestamoRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/libro/{titulo}")
    public List<Prestamo> ObtenerPorLibro(@PathVariable String titulo) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        return service.obtenerPorLibroTitulo(titulo);
    }

    @GetMapping("/persona/{persona}")
    public Prestamo obtenerPorPersona(@PathVariable String persona) {
        return service.obtenerPorPersona(persona);
    }

}
