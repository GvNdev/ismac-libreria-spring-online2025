package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibroControllerUnitTest {
    @InjectMocks
    private LibroController libroController;

    @Mock
    private LibroService libroService;

    private Categoria categoria;
    private Autor autor;
    private Libro libro;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria(1, "Mitología", "Relatos que forman parte de una determinada religión o cultura");
        autor = new Autor(1, "Patrick", "Süskind", "Germany", "Ambach", "1234567890", "psuskind@mail.com");
        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitle("La Niebla");
        libro.setEditorial("Debolsillo");
        libro.setPages(320);
        libro.setEdition("Primera edición");
        libro.setLanguage("español");
        libro.setPublicationDate(LocalDateTime.now());
        libro.setDescription("Niebla oculta monstruos de otro mundo");
        libro.setCoverType("Pasta Dura");
        libro.setISBN("9788483468012");
        libro.setCopies(5);
        libro.setCover("Normal");
        libro.setPresentation("Librerías y web");
        libro.setPrice(55.55);
        libro.setCategoria(categoria);
        libro.setAutor(autor);
    }

    @Test
    public void testFindAll() {
        when(libroService.findAll()).thenReturn(List.of(libro));
        ResponseEntity<List<Libro>> response = libroController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(libroService, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(libroService.findById(1)).thenReturn(libro);
        ResponseEntity<Libro> response = libroController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(libroService.findById(2)).thenReturn(null);
        ResponseEntity<Libro> response = libroController.findById(2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(libroService.save(libro)).thenReturn(libro);
        ResponseEntity<Libro> response = libroController.save(libro);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("9788483468012", response.getBody().getISBN());
    }

    @Test
    public void testUpdateIfExists() {
        when(libroService.update(1, libro)).thenReturn(libro);
        ResponseEntity<Libro> response = libroController.update(1, libro);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateIfNotExists() {
        when(libroService.update(eq(2), any(Libro.class))).thenReturn(null);
        ResponseEntity<Libro> response = libroController.update(2, libro);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(libroService).delete(1);
        ResponseEntity<Void> response = libroController.delete(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(libroService, times(1)).delete(1);
    }
}
