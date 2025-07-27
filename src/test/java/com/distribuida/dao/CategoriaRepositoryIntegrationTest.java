package com.distribuida.dao;

import com.distribuida.model.Categoria;
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
public class CategoriaRepositoryIntegrationTest {
    @Autowired
    private CategoriaRepository categoriaRepository;

    private final int newCategoriaId = 58;
    StringBuilder sb = new StringBuilder();

    @Test
    public void findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);
        for (Categoria categoria : categorias) {
            System.out.println(categoria.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        assertTrue(categoria.isPresent());
        System.out.println(categoria.toString());
    }

    @Test
    public void save() {
        String newCategory = "Poesía";
        String newDescription = "Una manifestación de la belleza o del sentimiento estético por medio de la palabra, en verso o en prosa.";
        Categoria categoria = new Categoria(0, newCategory, newDescription);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        assertNotNull(savedCategoria);
        assertEquals(newCategory, savedCategoria.getCategory());
        assertEquals(newDescription, savedCategoria.getDescription());
    }

    @Test
    public void update() {
        String updatedCategory = "Mitología";
        String updatedDescription = "Relatos que forman parte de una determinada religión o cultura";
        Optional<Categoria> categoria = categoriaRepository.findById(newCategoriaId);
        assertTrue(categoria.isPresent(), sb.append("Categoria with id_categoria=").append(newCategoriaId).append(" should exist").toString());
        categoria.orElse(null).setCategory(updatedCategory);
        categoria.orElse(null).setDescription(updatedDescription);
        Categoria updatedCategoria = categoriaRepository.save(categoria.orElse(null));
        assertNotNull(updatedCategoria);
        assertEquals(updatedCategory, updatedCategoria.getCategory());
        assertEquals(updatedDescription, updatedCategoria.getDescription());
    }

    @Test
    public void delete() {
        if (categoriaRepository.existsById(newCategoriaId)) {
            categoriaRepository.deleteById(newCategoriaId);
        }
        assertFalse(categoriaRepository.existsById(newCategoriaId), sb.append("Categoria with id_categoria=").append(newCategoriaId).append(" should have been deleted").toString());
    }
}
