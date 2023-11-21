package com.example.bibliotecaCatalogo.service;

import com.example.bibliotecaCatalogo.clases.Libro;
import com.example.bibliotecaCatalogo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class LibroService {
    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository){this.libroRepository = libroRepository;}

    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }
    public Libro getLibroById(Long id){
        return libroRepository.findById(id).orElseGet(null);
    }
    public List<Libro> getAllLibros(){
        return libroRepository.findAll();
    }


 public Libro updateLibro(Libro libro) {
     Optional<Libro> existingLibro = libroRepository.findById(libro.getId());
     if (existingLibro.isPresent()) {
         Libro updateLibro = existingLibro.get();
         updateLibro.setTitulo(libro.getTitulo());
         updateLibro.setAutor(libro.getAutor());
         updateLibro.setIsbn(libro.getIsbn());
         updateLibro.setPaginas(libro.getPaginas());
         return libroRepository.save(updateLibro);
     } else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro con Id=" + libro.getId() + "no encontrado");
     }
 }

    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
