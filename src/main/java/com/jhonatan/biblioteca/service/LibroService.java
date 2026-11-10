package com.jhonatan.biblioteca.service;

import com.jhonatan.biblioteca.dto.LibroRequest;
import com.jhonatan.biblioteca.dto.LibroResponse;
import com.jhonatan.biblioteca.model.Autor;
import com.jhonatan.biblioteca.model.Libro;
import com.jhonatan.biblioteca.repository.AutorRepository;
import com.jhonatan.biblioteca.repository.LibroRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {
    private final LibroRepository repository;
    private final AutorRepository autorRepository;


    public LibroService(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public List<Libro> listar() {
        return repository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("no existe ese libro"));
    }

    @Transactional
    public LibroResponse guardar(LibroRequest request) {
        Libro libroNuevo = convertirAEntidad(request);
        Libro libroGuardado = repository.save(libroNuevo);
        return convertirAResponse(libroGuardado);
    }

    //metodos para mapear
    //trasformamos de request a modelo
    private Libro convertirAEntidad(LibroRequest request) {
        //obtenemos los datos del autor mediante el id
        Autor autor = autorRepository.findById(request.getAutorId()).orElseThrow(() -> new RuntimeException("No existe ese Autor con el id " + request.getAutorId()));
        Libro libro = new Libro();
        libro.setTitulo(request.getTitulo());
        libro.setIsbn(request.getIsbn());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        //pasamos el autor y spring se encarga de sacar el id al objeto
        libro.setAutor(autor);
        return libro;
    }

    //trasformamos de modelo a request
    private LibroResponse convertirAResponse(Libro libro) {
        LibroResponse response = new LibroResponse();
        response.setId(libro.getId());
        response.setTitulo(libro.getTitulo());
        response.setIsBn(libro.getIsbn());
        response.setAnioPublicacion(libro.getAnioPublicacion());
        response.setAutorId(libro.getAutor().getId());
        return response;
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Libro buscarPorTitulo(String titulo) {
        return repository.findByTitulo(titulo).orElseThrow(() -> new RuntimeException("no existe ese libro"));
    }

    public List<Libro> buscarPorAutor(Autor autor) {
        return repository.findByAutor(autor);
    }

    public List<LibroResponse> buscarPorNombreAutor(String nombre) {
        List<Libro> libros = repository.findByAutorNombre(nombre);
        List<LibroResponse> respuesta = new ArrayList<>();
        for (Libro libro : libros) {
           respuesta.add(convertirAResponse(libro));
        }
        return respuesta;
    }

}
