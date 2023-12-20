package com.libros.libros.repositories;

import com.libros.libros.models.Libro;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface LibroRepository extends R2dbcRepository<Libro, Long> {

    Flux<Libro> findByAutor(String autor);

    Flux<Libro> findAll();

    Mono<Libro> findById(Long id);

    Mono<Libro> save(Libro libro);

    Mono<Void> deleteById(Long id);
}
