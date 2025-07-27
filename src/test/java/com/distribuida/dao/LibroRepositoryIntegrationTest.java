package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroRepositoryIntegrationTest {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final int newLibroId = 79;
    private Libro libro;
    Optional<Categoria> categoria;
    Optional<Autor> autor;

    @BeforeEach
    public void setup() {
        categoria = categoriaRepository.findById(1);
        autor = autorRepository.findById(1);
        libro = new Libro();
    }

    @Test
    public void findAll() {
        List<Libro> libros = libroRepository.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() > 0);
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Libro> libro = libroRepository.findById(19);
        assertNotNull(libro);
        assertEquals("La Odisea", libro.orElse(null).getTitle());
        System.out.println(libro.toString());
    }

    @Test
    public void save() {
        Optional<Autor> autor = autorRepository.findById(36);
        Optional<Categoria> categoria = categoriaRepository.findById(21);
        String newTitle = "La Niebla";
        String newDescription = "Niebla oculta monstruos de otro mundo";
        assertTrue(autor.isPresent());
        assertTrue(categoria.isPresent());

        Libro libro = new Libro();
        libro.setIdLibro(0);
        libro.setTitle(newTitle);
        libro.setEditorial("Debolsillo");
        libro.setPages(320);
        libro.setEdition("Primera edición");
        libro.setLanguage("español");
        libro.setPublicationDate(LocalDateTime.now());
        libro.setDescription(newDescription);
        libro.setCoverType("Pasta Dura");
        libro.setISBN("9788483468012");
        libro.setCopies(5);
        libro.setCover("Normal");
        libro.setPresentation("Librerías y web");
        libro.setPrice(55.55);
        libro.setCategoria(categoria.orElse(null));
        libro.setAutor(autor.orElse(null));
        Libro savedLibro = libroRepository.save(libro);

        assertNotNull(savedLibro);
        assertEquals(newTitle, savedLibro.getTitle());
        assertEquals(newDescription, savedLibro.getDescription());
    }

    @Test
    public void update() {
        Optional<Libro> libro = libroRepository.findById(newLibroId);
        Optional<Autor> autor = autorRepository.findById(50);
        Optional<Categoria> categoria = categoriaRepository.findById(28);
        String newISBN = "9788432232473";

        assertNotNull(libro);
        assertNotNull(autor);
        assertNotNull(categoria);

        libro.orElse(null).setTitle("Veinte poemas de amor y una canción desesperada");
        libro.orElse(null).setEditorial("Debolsillo");
        libro.orElse(null).setPages(176);
        libro.orElse(null).setEdition("Primera edición");
        libro.orElse(null).setLanguage("español");
        libro.orElse(null).setPublicationDate(LocalDateTime.now());
        libro.orElse(null).setDescription("Poemario basado en experiencias amorosas");
        libro.orElse(null).setCoverType("Pasta Dura");
        libro.orElse(null).setISBN(newISBN);
        libro.orElse(null).setCopies(6);
        libro.orElse(null).setCover("Normal");
        libro.orElse(null).setPresentation("Librerías y web");
        libro.orElse(null).setPrice(66.66);
        libro.orElse(null).setCategoria(categoria.orElse(null));
        libro.orElse(null).setAutor(autor.orElse(null));

        Libro updatedLibro = libroRepository.save(libro.orElse(null));

        assertNotNull(updatedLibro);
        assertEquals(newISBN, updatedLibro.getISBN());
    }

    @Test
    public void delete() {
        if (libroRepository.existsById(newLibroId)) {
            libroRepository.deleteById(newLibroId);
        }
        assertFalse(libroRepository.existsById(newLibroId));
    }
}
