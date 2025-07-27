package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaUnitTest {
    private Categoria categoria;

    @BeforeEach
    public void setup() {
        categoria = new Categoria(1, "Horror", "It provokes fear and terror in the viewer or reader, through the narration of stories that explore dark themes.");
    }

    @Test
    public void testConstructorAndGetters() {
        assertAll("Validar constructor y getters de categoría",
                () -> assertEquals(1, categoria.getIdCategoria()),
                () -> assertEquals("Horror", categoria.getCategory()),
                () -> assertEquals("It provokes fear and terror in the viewer or reader, through the narration of stories that explore dark themes.", categoria.getDescription()));
    }

    @Test
    public void testSetters() {
        categoria = new Categoria();
        categoria.setIdCategoria(2);
        categoria.setCategory("Crime fiction");
        categoria.setDescription("Describe narratives or fiction that centre on criminal acts and especially on the investigation, either by an amateur or a professional detective, of a crime, often a murder.");

        assertAll("Validar setters de categoría",
                () -> assertEquals(2, categoria.getIdCategoria()),
                () -> assertEquals("Crime fiction", categoria.getCategory()),
                () -> assertEquals("Describe narratives or fiction that centre on criminal acts and especially on the investigation, either by an amateur or a professional detective, of a crime, often a murder.", categoria.getDescription()));
    }

    @Test
    public void testToString() {
        String str = categoria.toString();

        assertAll("Validar toString de categoría",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Horror")),
                () -> assertTrue(str.contains("It provokes fear and terror in the viewer or reader, through the narration of stories that explore dark themes.")));
    }
}
