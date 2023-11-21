package com.example.bibliotecaCatalogo.controller;
import com.example.bibliotecaCatalogo.clases.Libro;
import com.example.bibliotecaCatalogo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class LibroController {
    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/actuator/health")
    public String health(){
        return "OK";
    }

    @GetMapping("/actuator/metrics")
    public String metrics() {
        return "Metricas";
    }

    @GetMapping("/actuator/info")
    public String info() {
        return "Info";
    }


    @PostMapping("/")
    public Libro saveLibro(Long id){
        return libroService.getLibroById(id);
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAll(){
        List<Libro> libros = libroService.getAllLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getById(@PathVariable Long id){
        Optional<Libro> libro = Optional.ofNullable(libroService.getLibroById(id));
        return libro.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody Libro libro) {
        libroService.saveLibro(libro);
        return new ResponseEntity<>(libro, HttpStatus.CREATED);
    }



   @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro ){
        Optional<Libro> existingLibro = Optional.ofNullable(libroService.getLibroById(id));
        if (existingLibro.isPresent()) {
            libro.setId(existingLibro.get().getId());
            libroService.saveLibro(libro);
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        libroService.deleteLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
