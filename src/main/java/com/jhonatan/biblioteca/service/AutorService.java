package com.jhonatan.biblioteca.service;

import com.jhonatan.biblioteca.dto.AutorRequest;
import com.jhonatan.biblioteca.dto.AutorResoponse;
import com.jhonatan.biblioteca.model.Autor;
import com.jhonatan.biblioteca.repository.AutorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> listar() {
        return repository.findAll();
    }

    public Autor obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("no existe ese id"));
    }

    @Transactional
    public AutorResoponse guardar(AutorRequest request) {
    Autor autorNuevo = convertirAEntidad(request);
    Autor autorGuardado = repository.save(autorNuevo);
    return convertirAResponse(autorGuardado);


    }
    //mappers
    // response a Entidad
    private Autor convertirAEntidad(AutorRequest request){
        Autor autorNuevo = new Autor();
        autorNuevo.setNombre(request.getNombre());
        autorNuevo.setNacionalidad(request.getNacionalidad());
        return autorNuevo;
    }

    private AutorResoponse convertirAResponse(Autor autor){
        AutorResoponse resoponse = new AutorResoponse();
        resoponse.setId(autor.getId());
        resoponse.setNombreAutor(autor.getNombre());
        resoponse.setNacionalidad(autor.getNacionalidad());
        return resoponse;
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Autor buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre).orElseThrow(() -> new RuntimeException("ese nombre no existe"));
    }
}
