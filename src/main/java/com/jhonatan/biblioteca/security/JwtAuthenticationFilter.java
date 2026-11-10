package com.jhonatan.biblioteca.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//esta clase extiende de OncePerRequestFilter le dice a sping "este filtro se debe ejecutar una ves  por cada ves
//por cada peticion q llege
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        //1. obtener el header authorization
        //el filtro busca en los headers de la peticion  digamos q postman envia el token  busca la clave en "Aurhorization"
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        // 2. Si no hay header o no empieza con "Bearer ", dejamos pasar (Spring Security lo rechazará si la ruta es protegida)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //3 ectraer el token quitando el bearer
        jwt = authHeader.substring(7); // Quita la palabra "Bearer " y deja solo el token

        //4 extraer el username del token
        username = jwtService.extractUsername(jwt); // Saca el nombre del usuario del token
        // 5. Si el username no es nulo y el usuario no está autenticado aún
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //busca los datos del usuario
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //valida el token
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //guarda la autenticacion en el contexto
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        //continua con la cadena de filtros
        filterChain.doFilter(request,response);
    }

}
