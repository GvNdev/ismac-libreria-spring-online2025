package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaRepositoryIntegrationTest {
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Factura factura;
    private Optional<Cliente> cliente;

    @BeforeEach
    public void setup() {
        cliente = clienteRepository.findById(1);
        factura = new Factura();
    }

    @Test
    public void findAll() {
        List<Factura> facturas = facturaRepository.findAll();
        assertNotNull(facturas);
        assertTrue(facturas.size() > 0);
        for (Factura factura : facturas) {
            System.out.println(factura.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Factura> factura = facturaRepository.findById(82);
        assertNotNull(factura);
        assertEquals("FAC-0061", factura.orElse(null).getBillNo());
        System.out.println(factura.toString());
    }

    @Test
    public void save() {
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertTrue(cliente.isPresent());
        Factura factura = new Factura();
        factura.setIdFactura(0);
        factura.setBillNo("FAC-00083");
        factura.setDate(new Date());
        factura.setNetTotal(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente.orElse(null));
        Factura savedFactura = facturaRepository.save(factura);
        assertNotNull(savedFactura);
        assertEquals("FAC-00083", savedFactura.getBillNo());
        assertEquals(115.00, savedFactura.getTotal());
    }

    @Test
    public void update() {
        Optional<Factura> factura = facturaRepository.findById(86);
        Optional<Cliente> cliente = clienteRepository.findById(2);

        assertNotNull(factura);
        assertNotNull(cliente);

        factura.orElse(null).setBillNo("FAC-10083");
        factura.orElse(null).setDate(new Date());
        factura.orElse(null).setNetTotal(200.00);
        factura.orElse(null).setIva(30.00);
        factura.orElse(null).setTotal(230.00);
        factura.orElse(null).setCliente(cliente.orElse(null));

        Factura updatedFactura = facturaRepository.save(factura.orElse(null));

        assertNotNull(updatedFactura);
        assertEquals("FAC-10083", updatedFactura.getBillNo());
    }

    @Test
    public void delete() {
        if (facturaRepository.existsById(86)) {
            facturaRepository.deleteById(86);
        }
        assertFalse(facturaRepository.existsById(86));
    }
}
