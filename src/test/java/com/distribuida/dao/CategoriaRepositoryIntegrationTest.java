package com.distribuida.dao;

import com.distribuida.model.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CategoriaRepositoryIntegrationTest {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        for (Categoria categoria : categorias) {
            System.out.println(categoria.toString());
        }
    }
}
