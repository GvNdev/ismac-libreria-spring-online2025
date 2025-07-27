package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorUnitTest {
    private Autor autor;

    @BeforeEach
    public void setup() {
        autor = new Autor(1, "Patrick", "Süskind", "Germany", "Ambach", "1234567890", "psuskind@mail.com");
    }

    @Test
    public void testConstructorAndGetters() {
        assertAll("Validar constructor y getters de autor",
                () -> assertEquals(1, autor.getIdAutor()),
                () -> assertEquals("Patrick", autor.getName()),
                () -> assertEquals("Süskind", autor.getLastName()),
                () -> assertEquals("Germany", autor.getCountry()),
                () -> assertEquals("Ambach", autor.getAddress()),
                () -> assertEquals("1234567890", autor.getPhone()),
                () -> assertEquals("psuskind@mail.com", autor.getEmail()));
    }

    @Test
    public void testSetters() {
        autor = new Autor();
        autor.setIdAutor(2);
        autor.setName("Umberto");
        autor.setLastName("Eco");
        autor.setCountry("Italy");
        autor.setAddress("Alessandria");
        autor.setPhone("1234567891");
        autor.setEmail("ueco@mail.com");

        assertAll("Validar setters de autor",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Umberto", autor.getName()),
                () -> assertEquals("Eco", autor.getLastName()),
                () -> assertEquals("Italy", autor.getCountry()),
                () -> assertEquals("Alessandria", autor.getAddress()),
                () -> assertEquals("1234567891", autor.getPhone()),
                () -> assertEquals("ueco@mail.com", autor.getEmail()));
    }

    @Test
    public void testToString() {
        String str = autor.toString();

        assertAll("Validar toString de autor",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Patrick")),
                () -> assertTrue(str.contains("Süskind")),
                () -> assertTrue(str.contains("Germany")),
                () -> assertTrue(str.contains("Ambach")),
                () -> assertTrue(str.contains("1234567890")),
                () -> assertTrue(str.contains("psuskind@mail.com")));
    }
}
