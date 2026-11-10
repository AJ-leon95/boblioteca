package com.jhonatan.biblioteca.service;

import com.jhonatan.biblioteca.dto.LibroRequest;
import com.jhonatan.biblioteca.dto.PrestamoRequest;
import com.jhonatan.biblioteca.dto.PrestamoResponse;
import com.jhonatan.biblioteca.model.Libro;
import com.jhonatan.biblioteca.model.Prestamo;
import com.jhonatan.biblioteca.repository.LibroRepository;
import com.jhonatan.biblioteca.repository.PrestamoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    private final PrestamoRepository repository;
    private final LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository repository, LibroRepository libroRepository) {
        this.repository = repository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> listar() {
        return repository.findAll();
    }

    public Prestamo obtenerPorId(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("no existe ese perstamo por el id"));
    }

    @Transactional
    public PrestamoResponse guardar(PrestamoRequest request) {
      //convertimos el request a una entidad
        Prestamo prestamoNuevo = new Prestamo();
        prestamoNuevo = convertirAEntidad(request);
        Prestamo prestamoGuardado = repository.save(prestamoNuevo);
        return convertirAResponse(prestamoGuardado);

    }
    //metodos de mappers
    //convertimos lo q manda el usuario a Entidad request
    private  Prestamo convertirAEntidad(PrestamoRequest request){
        //buscamos el libro en la bbdd
        Libro lib = libroRepository.findById(request.getLibroId()).orElseThrow(()->new RuntimeException("No existe el libro con ese id"));

        //armamos la entidad o modelo
        Prestamo prestamo = new Prestamo();
        prestamo.setNombrePersona(request.getNombrePersona());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setFechaDevolucion(request.getFechaDevolucion());
        prestamo.setLibro(lib); //asignamos el objeto completo y spring se encarga de sacar el id
        return prestamo;
    }

    //convertimos lo q devuelve java modelo a response
    private PrestamoResponse convertirAResponse(Prestamo prestamo){
        PrestamoResponse response = new PrestamoResponse();

        response.setId(prestamo.getId());
        response.setNombrePersona(prestamo.getNombrePersona());
        response.setFechaPrestamo(prestamo.getFechaPrestamo());
        response.setFechaDevolucion(prestamo.getFechaDevolucion());
        response.setLibroId(prestamo.getLibro().getId());
        return response;

    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Prestamo> obtenerPorLibroTitulo(String titulo) {
        return repository.findByLibroTitulo(titulo);
    }

    public Prestamo obtenerPorPersona(String nombre) {
        return repository.findByNombrePersona(nombre).orElseThrow(() -> new RuntimeException("esa persona no tiene prestamo"));
    }
}
