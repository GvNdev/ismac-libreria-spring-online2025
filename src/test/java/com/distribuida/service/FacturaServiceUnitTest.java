package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.service.impl.FacturaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceUnitTest {
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        factura = new Factura();
        cliente = new Cliente(1, "1234567890", "Gabriel", "Valdivieso", "Quito", "0999123456", "mail@mail.com");
        factura.setIdFactura(1);
        factura.setBillNo("FAC-0001");
        factura.setDate(new Date());
        factura.setNetTotal(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente);
    }

    @Test
    public void testFindAll() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        List<Factura> facturas = facturaService.findAll();
        assertEquals(1, facturas.size());
        assertNotNull(facturas);
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));
        Factura factura = facturaService.findById(1);
        assertNotNull(factura);
        assertEquals("FAC-0001", factura.getBillNo());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(facturaRepository.findById(2)).thenReturn(Optional.empty());
        Factura factura = facturaService.findById(2);
        assertNull(factura);
    }

    @Test
    public void testSave() {
        when(facturaRepository.save(factura)).thenReturn(factura);
        Factura savedFactura = facturaService.save(factura);
        assertEquals(115.00, savedFactura.getTotal());
    }

    @Test
    public void testUpdateIfExists() {
        Cliente newCliente = new Cliente(2, "1234567891", "Gabriel1", "Valdivieso1", "Quito1", "0999123451", "mail1@mail.com");
        Factura newFactura = new Factura();
        newFactura.setBillNo("FAC-1001");
        newFactura.setDate(new Date());
        newFactura.setNetTotal(200.00);
        newFactura.setIva(30.00);
        newFactura.setTotal(230.00);
        newFactura.setCliente(newCliente);

        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));
        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura updatedFactura = facturaService.update(1, newFactura);

        assertNotNull(updatedFactura);
        assertEquals("FAC-1001", updatedFactura.getBillNo());
        assertEquals(230.00, updatedFactura.getTotal());
        verify(facturaRepository, times(1)).save(factura);
    }

    @Test
    public void testUpdateIfNotExists() {
        Factura newFactura = new Factura();

        when(facturaRepository.findById(999)).thenReturn(Optional.empty());
        Factura updatedFactura = facturaService.update(999, newFactura);

        assertNull(updatedFactura);
        verify(facturaRepository, never()).save(any());
    }

    @Test
    public void testDeleteIfExists() {
        when(facturaRepository.existsById(1)).thenReturn(true);
        facturaService.delete(1);
        verify(facturaRepository).deleteById(1);
    }

    @Test
    public void testDeleteIfNotExists() {
        when(facturaRepository.existsById(999)).thenReturn(false);
        facturaService.delete(999);
        verify(facturaRepository, never()).deleteById(anyInt());
    }
}
