package com.libros.libros.service;

import com.libros.libros.models.Libro;
import com.libros.libros.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Flux<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Mono<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Mono<Libro> crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Mono<Libro> actualizarLibro(Long id, Libro libro) {
        return libroRepository.findById(id)
                .flatMap(existingLibro -> {
                    existingLibro.setTitulo(libro.getTitulo());
                    existingLibro.setAutor(libro.getAutor());
                    existingLibro.setAnioPublicacion(libro.getAnioPublicacion());
                    return libroRepository.save(existingLibro);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Void> eliminarLibro(Long id) {
        return libroRepository.deleteById(id);
    }

    public Flux<Libro> obtenerLibrosPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }
}
