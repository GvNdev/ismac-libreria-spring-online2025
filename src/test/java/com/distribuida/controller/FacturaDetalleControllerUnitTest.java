package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.FacturaDetalleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FacturaDetalleControllerUnitTest {
    @InjectMocks
    private FacturaDetalleController facturaDetalleController;

    @Mock
    private FacturaDetalleService facturaDetalleService;

    private Factura factura;
    private Cliente cliente;
    private Autor autor;
    private Categoria categoria;
    private Libro libro;
    private FacturaDetalle facturaDetalle;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        factura = new Factura(1, "123456", new Date(), 10.00, 1.20, 11.20, cliente);
        cliente = new Cliente(1, "1234567890", "Gabriel", "Valdivieso", "Quito", "0999123456", "mail@mail.com");
        autor = new Autor(1, "Patrick", "Süskind", "Germany", "Ambach", "1234567890", "psuskind@mail.com");
        categoria = new Categoria(1, "Mitología", "Relatos que forman parte de una determinada religión o cultura");
        libro = new Libro(1, "La Niebla", "Debolsillo", 320, "Primera edición", "español", LocalDateTime.now(), "Niebla oculta monstruos de otro mundo", "Pasta Dura", "9788483468012", 5, "Normal", "Librerías y web", 55.55, categoria, autor);
        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setQ(2);
        facturaDetalle.setNetTotal(10.00);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }

    @Test
    public void testFindAll() {
        when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));
        ResponseEntity<List<FacturaDetalle>> response = facturaDetalleController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(facturaDetalleService, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(facturaDetalleService.findById(1)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> response = facturaDetalleController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(facturaDetalle.getNetTotal(), response.getBody().getNetTotal());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(facturaDetalleService.findById(2)).thenReturn(null);
        ResponseEntity<FacturaDetalle> response = facturaDetalleController.findById(2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(facturaDetalleService.save(facturaDetalle)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> response = facturaDetalleController.save(facturaDetalle);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("123456", response.getBody().getFactura().getBillNo());
    }

    @Test
    public void testUpdateIfExists() {
        when(facturaDetalleService.update(1, facturaDetalle)).thenReturn(facturaDetalle);
        ResponseEntity<FacturaDetalle> response = facturaDetalleController.update(1, facturaDetalle);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateIfNotExists() {
        when(facturaDetalleService.update(eq(2), any(FacturaDetalle.class))).thenReturn(null);
        ResponseEntity<FacturaDetalle> response = facturaDetalleController.update(2, facturaDetalle);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(facturaDetalleService).delete(1);
        ResponseEntity<Void> response = facturaDetalleController.delete(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(facturaDetalleService, times(1)).delete(1);
    }
}
