package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
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
public class ClienteRepositoryIntegrationTest {
    @Autowired
    private ClienteRepository clienteRepository;

    private final int newClienteId = 40;
    StringBuilder sb = new StringBuilder();

    @Test
    public void findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertTrue(cliente.isPresent());
        System.out.println(cliente.toString());
    }

    @Test
    public void save() {
        String newIdentification = "1234567890";
        String newName = "Gabriel";
        String newLastName = "Valdivieso";
        String newAddress = "Loma Grande";
        String newPhone = "0999123456";
        String newEmail = "mail@mail.com";
        Cliente cliente = new Cliente(0, newIdentification, newName, newLastName, newAddress, newPhone, newEmail);
        Cliente savedCliente = clienteRepository.save(cliente);
        assertNotNull(savedCliente);
        assertEquals(newIdentification, savedCliente.getIdentification());
        assertEquals(newName, savedCliente.getName());
        assertEquals(newLastName, savedCliente.getLastName());
        assertEquals(newAddress, savedCliente.getAddress());
        assertEquals(newPhone, savedCliente.getPhone());
        assertEquals(newEmail, savedCliente.getEmail());
    }

    @Test
    public void update() {
        String updatedIdentification = "1234567891";
        String updatedName = "Gabriel1";
        String updatedLastName = "Valdivieso1";
        String updatedAddress = "Loma Grande centro";
        String updatedPhone = "0989123456";
        String updatedEmail = "mail1@mail.com";
        Optional<Cliente> cliente = clienteRepository.findById(newClienteId);
        assertTrue(cliente.isPresent(), sb.append("Cliente with id_cliente=").append(newClienteId).append(" should exist").toString());
        cliente.orElse(null).setIdentification(updatedIdentification);
        cliente.orElse(null).setName(updatedName);
        cliente.orElse(null).setLastName(updatedLastName);
        cliente.orElse(null).setAddress(updatedAddress);
        cliente.orElse(null).setPhone(updatedPhone);
        cliente.orElse(null).setEmail(updatedEmail);
        Cliente updatedCliente = clienteRepository.save(cliente.orElse(null));
        assertNotNull(updatedCliente);
        assertEquals(updatedIdentification, updatedCliente.getIdentification());
        assertEquals(updatedName, updatedCliente.getName());
        assertEquals(updatedLastName, updatedCliente.getLastName());
        assertEquals(updatedAddress, updatedCliente.getAddress());
        assertEquals(updatedPhone, updatedCliente.getPhone());
        assertEquals(updatedEmail, updatedCliente.getEmail());
    }

    @Test
    public void delete() {
        if (clienteRepository.existsById(newClienteId)) {
            clienteRepository.deleteById(newClienteId);
        }
        assertFalse(clienteRepository.existsById(newClienteId), sb.append("Cliente with id_cliente=").append(newClienteId).append(" should have been deleted").toString());
    }
}
