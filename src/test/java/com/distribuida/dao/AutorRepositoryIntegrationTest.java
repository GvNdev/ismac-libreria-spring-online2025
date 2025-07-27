package com.distribuida.dao;

import com.distribuida.model.Autor;
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
public class AutorRepositoryIntegrationTest {
    @Autowired
    private AutorRepository autorRepository;

    private final int newAutorId = 54;
    StringBuilder sb = new StringBuilder();

    @Test
    public void findAll() {
        List<Autor> autores = autorRepository.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0);
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Autor> autor = autorRepository.findById(1);
        assertTrue(autor.isPresent());
        System.out.println(autor.toString());
    }

    @Test
    public void save() {
        String newName = "Patrick";
        String newLastName = "Süskind";
        String newCountry = "Germany";
        String newAddress = "Ambach";
        String newPhone = "1234567890";
        String newEmail = "psuskind@mail.com";
        Autor autor = new Autor(0, newName, newLastName, newCountry, newAddress, newPhone, newEmail);
        Autor savedAutor = autorRepository.save(autor);
        assertNotNull(savedAutor);
        assertEquals(newName, savedAutor.getName());
        assertEquals(newLastName, savedAutor.getLastName());
        assertEquals(newCountry, savedAutor.getCountry());
        assertEquals(newAddress, savedAutor.getAddress());
        assertEquals(newPhone, savedAutor.getPhone());
        assertEquals(newEmail, savedAutor.getEmail());
    }

    @Test
    public void update() {
        String updatedName = "Isabel";
        String updatedLastName = "Allende";
        String updatedCountry = "Perú";
        String updatedAddress = "Lima";
        String updatedPhone = "1234567891";
        String updatedEmail = "iallende@mail.com";
        Optional<Autor> autor = autorRepository.findById(newAutorId);
        assertTrue(autor.isPresent(), sb.append("Autor with id_autor=").append(newAutorId).append(" should exist").toString());
        autor.orElse(null).setName(updatedName);
        autor.orElse(null).setLastName(updatedLastName);
        autor.orElse(null).setCountry(updatedCountry);
        autor.orElse(null).setAddress(updatedAddress);
        autor.orElse(null).setPhone(updatedPhone);
        autor.orElse(null).setEmail(updatedEmail);
        Autor updatedAutor = autorRepository.save(autor.orElse(null));
        assertNotNull(updatedAutor);
        assertEquals(updatedName, updatedAutor.getName());
        assertEquals(updatedLastName, updatedAutor.getLastName());
        assertEquals(updatedCountry, updatedAutor.getCountry());
        assertEquals(updatedAddress, updatedAutor.getAddress());
        assertEquals(updatedPhone, updatedAutor.getPhone());
        assertEquals(updatedEmail, updatedAutor.getEmail());
    }

    @Test
    public void delete() {
        if (autorRepository.existsById(newAutorId)) {
            autorRepository.deleteById(newAutorId);
        }
        assertFalse(autorRepository.existsById(newAutorId), sb.append("Autor with id_autor=").append(newAutorId).append(" should have been deleted").toString());
    }
}
