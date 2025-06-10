package com.distribuida.service.impl;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
import com.distribuida.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(int id, Cliente cliente) {
        Cliente foundCliente = findById(id);

        if (foundCliente == null) {
            return null;
        }

        foundCliente.setIdentification(cliente.getIdentification());
        foundCliente.setName(cliente.getName());
        foundCliente.setLastName(cliente.getLastName());
        foundCliente.setAddress(cliente.getAddress());
        foundCliente.setPhone(cliente.getPhone());
        foundCliente.setEmail(cliente.getEmail());

        return clienteRepository.save(foundCliente);
    }

    @Override
    public void delete(int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        }
    }
}
