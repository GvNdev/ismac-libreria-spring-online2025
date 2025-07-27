package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LibroUnitTest {
    private Autor autor;
    private Categoria categoria;
    private Libro libro;
    private LocalDateTime libroLocalDateTime = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        categoria = new Categoria(1, "Mitología", "Relatos que forman parte de una determinada religión o cultura");
        autor = new Autor(1, "Patrick", "Süskind", "Germany", "Ambach", "1234567890", "psuskind@mail.com");
        libro = new Libro(1, "La Niebla", "Debolsillo", 320, "Primera edición", "español", libroLocalDateTime, "Niebla oculta monstruos de otro mundo", "Pasta Dura", "9788483468012", 5, "Normal", "Librerías y web", 55.55, categoria, autor);
    }

    @Test
    public void testConstructorAndGetters() {
        assertAll("Validar constructor y getters de libro",
                () -> assertEquals(1, libro.getIdLibro()),
                () -> assertEquals("La Niebla", libro.getTitle()),
                () -> assertEquals("Debolsillo", libro.getEditorial()),
                () -> assertEquals(320, libro.getPages()),
                () -> assertEquals("Primera edición", libro.getEdition()),
                () -> assertEquals("español", libro.getLanguage()),
                () -> assertEquals(libroLocalDateTime, libro.getPublicationDate()),
                () -> assertEquals("Niebla oculta monstruos de otro mundo", libro.getDescription()),
                () -> assertEquals("Pasta Dura", libro.getCoverType()),
                () -> assertEquals("9788483468012", libro.getISBN()),
                () -> assertEquals(5, libro.getCopies()),
                () -> assertEquals("Normal", libro.getCover()),
                () -> assertEquals("Librerías y web", libro.getPresentation()),
                () -> assertEquals(55.55, libro.getPrice()),
                () -> assertEquals("Mitología", libro.getCategoria().getCategory()),
                () -> assertEquals("psuskind@mail.com", libro.getAutor().getEmail()));
    }

    @Test
    public void testSetters() {
        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitle("La Niebla");
        libro.setEditorial("Debolsillo");
        libro.setPages(320);
        libro.setEdition("Primera edición");
        libro.setLanguage("español");
        libro.setPublicationDate(libroLocalDateTime);
        libro.setDescription("Niebla oculta monstruos de otro mundo");
        libro.setCoverType("Pasta Dura");
        libro.setISBN("9788483468012");
        libro.setCopies(5);
        libro.setCover("Normal");
        libro.setPresentation("Librerías y web");
        libro.setPrice(55.55);
        libro.setCategoria(categoria);
        libro.setAutor(autor);

        assertAll("Validar setters de libro",
                () -> assertEquals(1, libro.getIdLibro()),
                () -> assertEquals("La Niebla", libro.getTitle()),
                () -> assertEquals("Debolsillo", libro.getEditorial()),
                () -> assertEquals(320, libro.getPages()),
                () -> assertEquals("Primera edición", libro.getEdition()),
                () -> assertEquals("español", libro.getLanguage()),
                () -> assertEquals(libroLocalDateTime, libro.getPublicationDate()),
                () -> assertEquals("Niebla oculta monstruos de otro mundo", libro.getDescription()),
                () -> assertEquals("Pasta Dura", libro.getCoverType()),
                () -> assertEquals("9788483468012", libro.getISBN()),
                () -> assertEquals(5, libro.getCopies()),
                () -> assertEquals("Normal", libro.getCover()),
                () -> assertEquals("Librerías y web", libro.getPresentation()),
                () -> assertEquals(55.55, libro.getPrice()),
                () -> assertEquals("Mitología", libro.getCategoria().getCategory()),
                () -> assertEquals("psuskind@mail.com", libro.getAutor().getEmail()));
    }

    @Test
    public void testToString() {
        String str = libro.toString();

        assertAll("Validar toString de libro",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("La Niebla")),
                () -> assertTrue(str.contains("Debolsillo")),
                () -> assertTrue(str.contains("320")),
                () -> assertTrue(str.contains("Primera edición")),
                () -> assertTrue(str.contains("español")),
                () -> assertTrue(str.contains(libroLocalDateTime.toString())),
                () -> assertTrue(str.contains("Niebla oculta monstruos de otro mundo")),
                () -> assertTrue(str.contains("Pasta Dura")),
                () -> assertTrue(str.contains("9788483468012")),
                () -> assertTrue(str.contains("5")),
                () -> assertTrue(str.contains("Normal")),
                () -> assertTrue(str.contains("Librerías y web")),
                () -> assertTrue(str.contains("55.55")),
                () -> assertTrue(str.contains("Mitología")),
                () -> assertTrue(str.contains("psuskind@mail.com")));
    }
}
