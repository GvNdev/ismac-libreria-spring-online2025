package com.distribuida.controller;

import com.distribuida.model.Cliente;
import com.distribuida.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerUnitTest {
    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setIdentification("1234567890");
        cliente.setName("Gabriel");
        cliente.setLastName("Valdivieso");
        cliente.setAddress("Loma Grande");
        cliente.setPhone("0999123456");
        cliente.setEmail("mail@mail.com");
    }

    @Test
    public void testFindAll() {
        when(clienteService.findAll()).thenReturn(List.of(cliente));
        ResponseEntity<List<Cliente>> response = clienteController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(clienteService, times(1)).findAll();

    }

    @Test
    public void testFindByIdIfExists() {
        when(clienteService.findById(1)).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente.getName(), response.getBody().getName());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(clienteService.findById(2)).thenReturn(null);
        ResponseEntity<Cliente> response = clienteController.findById(2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(clienteService.save(cliente)).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.save(cliente);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Gabriel", response.getBody().getName());
    }

    @Test
    public void testUpdateIfExists() {
        when(clienteService.update(1, cliente)).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.update(1, cliente);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateIfNotExists() {
        when(clienteService.update(eq(2), any(Cliente.class))).thenReturn(null);
        ResponseEntity<Cliente> response = clienteController.update(2, cliente);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(clienteService).delete(1);
        ResponseEntity<Void> response = clienteController.delete(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(clienteService, times(1)).delete(1);
    }
}
