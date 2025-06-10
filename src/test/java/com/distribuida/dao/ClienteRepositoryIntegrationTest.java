package com.distribuida.dao;

import com.distribuida.model.Cliente;
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
public class ClienteRepositoryIntegrationTest {
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }
}
