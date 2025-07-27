package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleUnitTest {
    private Factura factura;
    private Cliente cliente;
    private Autor autor;
    private Categoria categoria;
    private Libro libro;
    private FacturaDetalle facturaDetalle;
    private Date facturaDate = new Date();
    private LocalDateTime libroLocalDateTime = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        factura = new Factura(1, "123456", facturaDate, 10.00, 1.20, 11.20, cliente);
        cliente = new Cliente(1, "1234567890", "Gabriel", "Valdivieso", "Quito", "0999123456", "mail@mail.com");
        autor = new Autor(1, "Patrick", "Süskind", "Germany", "Ambach", "1234567890", "psuskind@mail.com");
        categoria = new Categoria(1, "Mitología", "Relatos que forman parte de una determinada religión o cultura");
        libro = new Libro(1, "La Niebla", "Debolsillo", 320, "Primera edición", "español", libroLocalDateTime, "Niebla oculta monstruos de otro mundo", "Pasta Dura", "9788483468012", 5, "Normal", "Librerías y web", 55.55, categoria, autor);
        facturaDetalle = new FacturaDetalle(1, 2, 10.00, factura, libro);
    }

    @Test
    public void testConstructorAndGetters() {
        assertAll("Validar constructor y getters de facturaDetalle",
                () -> assertEquals(1, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(2, facturaDetalle.getQ()),
                () -> assertEquals(10.00, facturaDetalle.getNetTotal()),
                () -> assertEquals("123456", facturaDetalle.getFactura().getBillNo()),
                () -> assertEquals("La Niebla", facturaDetalle.getLibro().getTitle()));
    }

    @Test
    public void testSetters() {
        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setQ(2);
        facturaDetalle.setNetTotal(10.00);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);

        assertAll("Validar setters de facturaDetalle",
                () -> assertEquals(1, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(2, facturaDetalle.getQ()),
                () -> assertEquals(10.00, facturaDetalle.getNetTotal()),
                () -> assertEquals("123456", facturaDetalle.getFactura().getBillNo()),
                () -> assertEquals("La Niebla", facturaDetalle.getLibro().getTitle()));
    }

    @Test
    public void testToString() {
        String str = facturaDetalle.toString();

        assertAll("Validar toString de facturaDetalle",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("10.0")),
                () -> assertTrue(str.contains("123456")),
                () -> assertTrue(str.contains("La Niebla")));
    }
}
