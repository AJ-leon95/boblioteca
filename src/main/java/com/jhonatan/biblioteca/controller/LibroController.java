package com.jhonatan.biblioteca.controller;

import com.jhonatan.biblioteca.dto.LibroRequest;
import com.jhonatan.biblioteca.dto.LibroResponse;
import com.jhonatan.biblioteca.model.Autor;
import com.jhonatan.biblioteca.model.Libro;
import com.jhonatan.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Libro> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public LibroResponse guardar(@Valid @RequestBody LibroRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/titulo/{titulo}")
    public Libro buscarPorTitulo(@PathVariable String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    @GetMapping("/buscar/{autorId}")
    public List<Libro> buscarPorAutor(@PathVariable Long autorId) {
        Autor autor = new Autor();
        autor.setId(autorId);
        return service.buscarPorAutor(autor);
    }
    @GetMapping("/buscar/{nombre}")
    public List<LibroResponse> buscarPorAutorNombre(@PathVariable String nombre) {
        return service.buscarPorNombreAutor(nombre);
    }
}
