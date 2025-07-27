package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.service.AutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AutorControllerUnitTest {
    @InjectMocks
    private AutorController autorController;

    @Mock
    private AutorService autorService;

    private Autor autor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setName("Patrick");
        autor.setLastName("Süskind");
        autor.setCountry("Germany");
        autor.setAddress("Ambach");
        autor.setPhone("1234567890");
        autor.setEmail("psuskind@mail.com");
    }

    @Test
    public void testFindAll() {
        when(autorService.findAll()).thenReturn(List.of(autor));
        ResponseEntity<List<Autor>> response = autorController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(autorService, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(autorService.findById(1)).thenReturn(autor);
        ResponseEntity<Autor> response = autorController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(autor.getEmail(), response.getBody().getEmail());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(autorService.findById(2)).thenReturn(null);
        ResponseEntity<Autor> response = autorController.findById(2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(autorService.save(autor)).thenReturn(autor);
        ResponseEntity<Autor> response = autorController.save(autor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Patrick", response.getBody().getName());
    }

    @Test
    public void testUpdateIfExists() {
        when(autorService.update(1, autor)).thenReturn(autor);
        ResponseEntity<Autor> response = autorController.update(1, autor);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateIfNotExists() {
        when(autorService.update(eq(2), any(Autor.class))).thenReturn(null);
        ResponseEntity<Autor> response = autorController.update(2, autor);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(autorService).delete(1);
        ResponseEntity<Void> response = autorController.delete(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(autorService, times(1)).delete(1);
    }
}
