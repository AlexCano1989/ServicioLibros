package com.libros.libros.controllers;

import com.libros.libros.models.Libro;
import com.libros.libros.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public Flux<Libro> obtenerLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Obtener un libro por ID")
    public Mono<Libro> obtenerLibroPorId(@PathVariable @ApiParam("ID del libro") Long id) {
        return libroRepository.findById(id);
    }

    @PostMapping
    public Mono<Libro> crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Mono<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroRepository.findById(id)
                .flatMap(existingLibro -> {
                    existingLibro.setTitulo(libro.getTitulo());
                    existingLibro.setAutor(libro.getAutor());
                    existingLibro.setAnioPublicacion(libro.getAnioPublicacion());
                    return libroRepository.save(existingLibro);
                })
                .switchIfEmpty(Mono.empty());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarLibro(@PathVariable Long id) {
        return libroRepository.deleteById(id);
    }

    @GetMapping("/porAutor/{autor}")
    public Flux<Libro> obtenerLibrosPorAutor(@PathVariable String autor) {
        return libroRepository.findByAutor(autor);
    }
}
