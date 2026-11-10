package com.jhonatan.biblioteca.controller;

import com.jhonatan.biblioteca.dto.LoginRequest;
import com.jhonatan.biblioteca.dto.LoginResponse;
import com.jhonatan.biblioteca.dto.UsuarioRequest;
import com.jhonatan.biblioteca.dto.UsuarioResponse;
import com.jhonatan.biblioteca.security.JwtService;
import com.jhonatan.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UsuarioService usuarioService;

    // Inyección de dependencias


    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // 1. Autenticar (verifica si el usuario y contraseña son correctos)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Si pasa, buscar el usuario y generar el token
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generarToken(user);

        // 3. Devolver el token
        return new LoginResponse(token);
    }
    @PostMapping("/registro")
    public UsuarioResponse registrar(@Valid @RequestBody UsuarioRequest request){
        return usuarioService.guardar(request);
    }
}