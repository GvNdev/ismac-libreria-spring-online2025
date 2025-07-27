package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
import com.distribuida.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceUnitTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
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
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testFindByIdIfExists() {
        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(cliente));
        Cliente cliente = clienteService.findById(1);
        assertNotNull(cliente);
        assertEquals("Gabriel", cliente.getName());
    }

    @Test
    public void testFindByIdIfNotExists() {
        when(clienteRepository.findById(2)).thenReturn(Optional.empty());
        Cliente cliente = clienteService.findById(2);
        assertNull(cliente);
    }

    @Test
    public void testSave() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente savedCliente = clienteService.save(cliente);
        assertEquals("Gabriel", savedCliente.getName());
    }

    @Test
    public void testUpdateIfExists() {
        Cliente newCliente = new Cliente();
        newCliente.setIdentification("1234567891");
        newCliente.setName("Gabriel1");
        newCliente.setLastName("Valdivieso1");
        newCliente.setAddress("Loma Grande centro");
        newCliente.setPhone("0989123456");
        newCliente.setEmail("mail1@mail.com");

        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(cliente));
        when(clienteRepository.save(any())).thenReturn(newCliente);

        Cliente updatedCliente = clienteService.update(1, newCliente);

        assertNotNull(updatedCliente);
        assertEquals("Gabriel1", updatedCliente.getName());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testUpdateIfNotExists() {
        Cliente newCliente = new Cliente();

        when(clienteRepository.findById(999)).thenReturn(Optional.empty());
        Cliente updatedCliente = clienteService.update(999, newCliente);

        assertNull(updatedCliente);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    public void testDeleteIfExists() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        clienteService.delete(1);
        verify(clienteRepository).deleteById(1);
    }

    @Test
    public void testDeleteIfNotExists() {
        when(clienteRepository.existsById(999)).thenReturn(false);
        clienteService.delete(999);
        verify(clienteRepository, never()).deleteById(anyInt());
    }
}
