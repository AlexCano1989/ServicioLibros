package com.libros.libros.repositories;

import com.libros.libros.models.Libro;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface LibroRepository extends ReactiveCrudRepository<Libro, Long> {

    Flux<Libro> findByAutor(String autor);
}
