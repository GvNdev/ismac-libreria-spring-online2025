package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaUnitTest {
    private Factura factura;
    private Cliente cliente;
    private Date facturaDate = new Date();

    @BeforeEach
    public void setup() {
        cliente = new Cliente(1, "1234567890", "Gabriel", "Valdivieso", "Quito", "0999123456", "mail@mail.com");
        factura = new Factura(1, "123456", facturaDate, 10.00, 1.20, 11.20, cliente);
    }

    @Test
    public void testConstructorAndGetters() {
        assertAll("Validar constructor y getters de factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("123456", factura.getBillNo()),
                () -> assertEquals(facturaDate, factura.getDate()),
                () -> assertEquals(10.00, factura.getNetTotal()),
                () -> assertEquals(1.20, factura.getIva()),
                () -> assertEquals(11.20, factura.getTotal()),
                () -> assertEquals("1234567890", factura.getCliente().getIdentification()));
    }

    @Test
    public void testSetters() {
        factura = new Factura();
        factura.setIdFactura(1);
        factura.setBillNo("123456");
        factura.setDate(facturaDate);
        factura.setNetTotal(10.00);
        factura.setIva(1.20);
        factura.setTotal(11.20);
        factura.setCliente(cliente);

        assertAll("Validar setters de factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("123456", factura.getBillNo()),
                () -> assertEquals(facturaDate, factura.getDate()),
                () -> assertEquals(10.00, factura.getNetTotal()),
                () -> assertEquals(1.20, factura.getIva()),
                () -> assertEquals(11.20, factura.getTotal()),
                () -> assertEquals("1234567890", factura.getCliente().getIdentification()));
    }

    @Test
    public void testToString() {
        String str = factura.toString();

        assertAll("Validar toString de cliente",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("123456")),
                () -> assertTrue(str.contains(facturaDate.toString())),
                () -> assertTrue(str.contains("10.0")),
                () -> assertTrue(str.contains("1.2")),
                () -> assertTrue(str.contains("11.2")),
                () -> assertTrue(str.contains("1234567890")));
    }
}
