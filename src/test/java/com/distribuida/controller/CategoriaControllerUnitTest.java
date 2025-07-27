package com.distribuida.controller;

import com.distribuida.model.Categoria;
import com.distribuida.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoriaControllerUnitTest {
    @InjectMocks
    private CategoriaController categoriaController;

    @Mock
    private CategoriaService categoriaService;

    private Categoria categoria;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategory("Crime fiction");
        categoria.setDescription("Describe narratives or fiction that centre on criminal acts and especially on the investigation, either by an amateur or a professional detective, of a crime, often a murder.");
    }

    @Test
    public void testFindAll() {
        when(categoriaService.findAll()).thenReturn(List.of(categoria));
        ResponseEntity<List<Categoria>> response = categoriaController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(categoriaService, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(categoriaService.findById(1)).thenReturn(categoria);
        ResponseEntity<Categoria> response = categoriaController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(categoria.getCategory(), response.getBody().getCategory());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(categoriaService.findById(2)).thenReturn(null);
        ResponseEntity<Categoria> response = categoriaController.findById(2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(categoriaService.save(categoria)).thenReturn(categoria);
        ResponseEntity<Categoria> response = categoriaController.save(categoria);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Crime fiction", response.getBody().getCategory());
    }

    @Test
    public void testUpdateIfExists() {
        when(categoriaService.update(1, categoria)).thenReturn(categoria);
        ResponseEntity<Categoria> response = categoriaController.update(1, categoria);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateIfNotExists() {
        when(categoriaService.update(eq(2), any(Categoria.class))).thenReturn(null);
        ResponseEntity<Categoria> response = categoriaController.update(2, categoria);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(categoriaService).delete(1);
        ResponseEntity<Void> response = categoriaController.delete(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(categoriaService, times(1)).delete(1);
    }
}
