package com.jhonatan.biblioteca.service;

import com.jhonatan.biblioteca.dto.UsuarioRequest;
import com.jhonatan.biblioteca.dto.UsuarioResponse;
import com.jhonatan.biblioteca.model.Usuario;
import com.jhonatan.biblioteca.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No existe usuario con ese id"));
    }

    @Transactional
    public UsuarioResponse guardar(UsuarioRequest request) {
        if (repository.existsByUsername(request.getUsername())){
            throw new RuntimeException("El username ya esta en uso");
        }
        if (repository.existsByCedula(request.getCedula())){
            throw  new RuntimeException("La cedula ya se encuentra registrada ");
        }
        // 1. Extraemos la fecha de nacimiento (La cédula ya fue validada por @Cedula)

        // 2. Armamos el usuario
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setUsername(request.getUsername());
        //asi se encripta la contraseña
        usuarioNuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioNuevo.setCedula(request.getCedula());
        usuarioNuevo.setRolUsu(request.getRolUsu());

        // 3. Guardamos en la base de datos
        Usuario usuarioGuardado = repository.save(usuarioNuevo);

        // 4. Convertimos a Response y devolvemos
        return convertirAResponse(usuarioGuardado);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // ==========================================
    // MÉTODOS PRIVADOS (MAPPERS Y LÓGICA)
    // ==========================================

    private UsuarioResponse convertirAResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setUserName(usuario.getUsername());
        response.setCedula(usuario.getCedula());
        response.setRolUsu(usuario.getRolUsu().name());

        // Calcular la edad
        LocalDate hoy = LocalDate.now();
        LocalDate nacimiento = usuario.getFechaNacimiento();

        if (nacimiento != null) {
            int edad = hoy.getYear() - nacimiento.getYear();
            if (hoy.getMonthValue() < nacimiento.getMonthValue() ||
                    (hoy.getMonthValue() == nacimiento.getMonthValue() && hoy.getDayOfMonth() < nacimiento.getDayOfMonth())) {
                edad--;
            }
            response.setEdad(edad);
        }

        return response;
    }

    private LocalDate extraerFechaNacimiento(String cedula) {
        // Como el validador ya comprobó que es real, solo extraemos los datos
        int mes = Integer.parseInt(cedula.substring(3, 5));
        int dia = Integer.parseInt(cedula.substring(5, 7));
        int anioCorto = Integer.parseInt(cedula.substring(7, 9));

        int anioActual = LocalDate.now().getYear();
        int anioNacimiento = (anioActual - anioCorto > 100) ? 1900 + anioCorto : 2000 + anioCorto;

        return LocalDate.of(anioNacimiento, mes, dia);
    }
}