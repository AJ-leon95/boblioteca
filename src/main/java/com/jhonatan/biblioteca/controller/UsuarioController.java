package com.jhonatan.biblioteca.controller;

import com.jhonatan.biblioteca.dto.UsuarioRequest;
import com.jhonatan.biblioteca.dto.UsuarioResponse;
import com.jhonatan.biblioteca.model.Usuario;
import com.jhonatan.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id){
        return service.obtenerPorId(id);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public UsuarioResponse guardar(@Valid @RequestBody UsuarioRequest request){
        return service.guardar(request);
    }
    @DeleteMapping("/{id}")
    public void  eliminar(@PathVariable Long id){
        service.eliminar(id);
    }

}
