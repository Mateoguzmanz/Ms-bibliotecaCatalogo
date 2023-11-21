package com.example.bibliotecaCatalogo;

import com.example.bibliotecaCatalogo.clases.Libro;
import com.example.bibliotecaCatalogo.controller.LibroController;
import com.example.bibliotecaCatalogo.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LibroControllerTest {
    @Mock
    private LibroService libroService;
    private LibroController libroController;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        libroController = new LibroController(libroService);
    }

    @Test
    public void testCrearLibro() {
        Libro libro = new Libro(1L, "prueba", "prueba", "prueba", 100);
        libro.setTitulo("El Quijote");
        libro.setAutor("Miguel de Cervantes");

        when(libroService.saveLibro(libro)).thenReturn(libro);
        ResponseEntity<Libro> response = libroController.create(libro);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(libro, response.getBody());
    }

    @Test
    public void testObtenerLibroPorId() {
        Long id = 1L;
        Libro libro = new Libro(1L, "prueba", "prueba", "prueba", 100);
        libro.setId(id);
        libro.setTitulo("El Quijote");
        libro.setAutor("Miguel de Cervantes");

        when(libroService.getLibroById(id)).thenReturn((libro));
        ResponseEntity<Libro> response = libroController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libro, response.getBody());
    }

    @Test
    public void testObtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        Libro libro1 = new Libro(2L, "prueba", "prueba", "prueba", 100);
        libro1.setId(1L);
        libro1.setTitulo("El quijote");
        libro1.setAutor("Miguel de Cervantes");
        libros.add(libro1);
        Libro libro2 = new Libro(3L, "prueba", "prueba", "prueba", 100);
        libro2.setId(2L);
        libro2.setTitulo("La Divina Comedia");
        libro2.setAutor("Dante Alighieri");
        libros.add(libro2);

        when(libroService.getAllLibros()).thenReturn(libros);
        ResponseEntity<List<Libro>> response = libroController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libros, response.getBody());
    }
}
