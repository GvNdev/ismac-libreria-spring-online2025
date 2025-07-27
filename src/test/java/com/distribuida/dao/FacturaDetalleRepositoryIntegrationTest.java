package com.distribuida.dao;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaDetalleRepositoryIntegrationTest {
    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private LibroRepository libroRepository;

    private final int newFacturaDetalleId = 211;
    private FacturaDetalle facturaDetalle;
    private Optional<Factura> factura;
    private Optional<Libro> libro;

    @BeforeEach
    public void setup() {
        factura = facturaRepository.findById(1);
        libro = libroRepository.findById(1);
        facturaDetalle = new FacturaDetalle();
    }

    @Test
    public void findAll() {
        List<FacturaDetalle> facturaDetalles = facturaDetalleRepository.findAll();
        assertNotNull(facturaDetalles);
        assertTrue(facturaDetalles.size() > 0);
        for (FacturaDetalle facturaDetalle : facturaDetalles) {
            System.out.println(facturaDetalle.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(26);
        assertNotNull(facturaDetalle);
        assertEquals(203.06, facturaDetalle.orElse(null).getNetTotal());
        System.out.println(facturaDetalle.toString());
    }

    @Test
    public void save() {
        Optional<Factura> factura = facturaRepository.findById(1);
        Optional<Libro> libro = libroRepository.findById(5);
        int newQ = 5;
        double newNetTotal = 5.55;
        assertTrue(factura.isPresent());

        FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(0);
        facturaDetalle.setQ(newQ);
        facturaDetalle.setNetTotal(newNetTotal);
        facturaDetalle.setFactura(factura.orElse(null));
        facturaDetalle.setLibro(libro.orElse(null));
        FacturaDetalle savedFacturaDetalle = facturaDetalleRepository.save(facturaDetalle);

        assertNotNull(savedFacturaDetalle);
        assertEquals(newQ, savedFacturaDetalle.getQ());
        assertEquals(newNetTotal, savedFacturaDetalle.getNetTotal());
    }

    @Test
    public void update() {
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(newFacturaDetalleId);
        Optional<Factura> factura = facturaRepository.findById(6);
        Optional<Libro> libro = libroRepository.findById(6);
        double updatedNetTotal = 6.66;

        assertNotNull(facturaDetalle);
        assertNotNull(factura);
        assertNotNull(libro);

        facturaDetalle.orElse(null).setQ(6);
        facturaDetalle.orElse(null).setNetTotal(updatedNetTotal);
        facturaDetalle.orElse(null).setFactura(factura.orElse(null));
        facturaDetalle.orElse(null).setLibro(libro.orElse(null));

        FacturaDetalle updatedFacturaDetalle = facturaDetalleRepository.save(facturaDetalle.orElse(null));

        assertNotNull(updatedFacturaDetalle);
        assertEquals(updatedNetTotal, updatedFacturaDetalle.getNetTotal());
    }

    @Test
    public void delete() {
        if (facturaDetalleRepository.existsById(newFacturaDetalleId)) {
            facturaDetalleRepository.deleteById(newFacturaDetalleId);
        }
        assertFalse(facturaDetalleRepository.existsById(newFacturaDetalleId));
    }
}
