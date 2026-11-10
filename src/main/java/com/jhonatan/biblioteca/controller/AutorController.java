package com.jhonatan.biblioteca.controller;

import com.jhonatan.biblioteca.dto.AutorRequest;
import com.jhonatan.biblioteca.dto.AutorResoponse;
import com.jhonatan.biblioteca.model.Autor;
import com.jhonatan.biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }
    //endpoinst
    @GetMapping
    public List<Autor> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public Autor obtenerPorId(@PathVariable Long id){
        return service.obtenerPorId(id);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public AutorResoponse guardar( @Valid @RequestBody AutorRequest request){
        return service.guardar(request);
    }
    @DeleteMapping("/{id}")
    public  void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
    @GetMapping("/buscar/{nombre}")
    public Autor buscarPorNombre(@PathVariable String nombre){
        return service.buscarPorNombre(nombre);
    }
}
