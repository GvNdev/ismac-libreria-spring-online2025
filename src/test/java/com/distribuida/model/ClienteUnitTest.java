package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteUnitTest {
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente(1, "1234567890", "Gabriel", "Valdivieso", "Quito", "0999123456", "mail@mail.com");
    }

    @Test
    public void testConstructorAndGetters() {
        assertAll("Validar constructor y getters de cliente",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1234567890", cliente.getIdentification()),
                () -> assertEquals("Gabriel", cliente.getName()),
                () -> assertEquals("Valdivieso", cliente.getLastName()),
                () -> assertEquals("Quito", cliente.getAddress()),
                () -> assertEquals("0999123456", cliente.getPhone()),
                () -> assertEquals("mail@mail.com", cliente.getEmail()));
    }

    @Test
    public void testSetters() {
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setIdentification("1234567890");
        cliente.setName("Gabriel");
        cliente.setLastName("Valdivieso");
        cliente.setAddress("Quito");
        cliente.setPhone("0999123456");
        cliente.setEmail("mail@mail.com");

        assertAll("Validar setters de cliente",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1234567890", cliente.getIdentification()),
                () -> assertEquals("Gabriel", cliente.getName()),
                () -> assertEquals("Valdivieso", cliente.getLastName()),
                () -> assertEquals("Quito", cliente.getAddress()),
                () -> assertEquals("0999123456", cliente.getPhone()),
                () -> assertEquals("mail@mail.com", cliente.getEmail()));
    }

    @Test
    public void testToString() {
        String str = cliente.toString();

        assertAll("Validar toString de cliente",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1234567890")),
                () -> assertTrue(str.contains("Gabriel")),
                () -> assertTrue(str.contains("Valdivieso")),
                () -> assertTrue(str.contains("Quito")),
                () -> assertTrue(str.contains("0999123456")),
                () -> assertTrue(str.contains("mail@mail.com")));
    }
}
