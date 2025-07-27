package com.distribuida.controller;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FacturaControllerUnitTest {
    @InjectMocks
    private FacturaController facturaController;

    @Mock
    private FacturaService facturaService;

    private Cliente cliente;
    private Factura factura;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1, "1234567890", "Gabriel", "Valdivieso", "Quito", "0999123456", "mail@mail.com");
        factura = new Factura();
        factura.setIdFactura(1);
        factura.setBillNo("123456");
        factura.setDate(new Date());
        factura.setNetTotal(10.00);
        factura.setIva(1.20);
        factura.setTotal(11.20);
        factura.setCliente(cliente);
    }

    @Test
    public void testFindAll() {
        when(facturaService.findAll()).thenReturn(List.of(factura));
        ResponseEntity<List<Factura>> response = facturaController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(facturaService, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(facturaService.findById(1)).thenReturn(factura);
        ResponseEntity<Factura> response = facturaController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(factura.getBillNo(), response.getBody().getBillNo());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(facturaService.findById(2)).thenReturn(null);
        ResponseEntity<Factura> response = facturaController.findById(2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(facturaService.save(factura)).thenReturn(factura);
        ResponseEntity<Factura> response = facturaController.save(factura);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(11.20, response.getBody().getTotal());
    }

    @Test
    public void testUpdateIfExists() {
        when(facturaService.update(1, factura)).thenReturn(factura);
        ResponseEntity<Factura> response = facturaController.update(1, factura);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateIfNotExists() {
        when(facturaService.update(eq(2), any(Factura.class))).thenReturn(null);
        ResponseEntity<Factura> response = facturaController.update(2, factura);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(facturaService).delete(1);
        ResponseEntity<Void> response = facturaController.delete(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(facturaService, times(1)).delete(1);
    }
}
