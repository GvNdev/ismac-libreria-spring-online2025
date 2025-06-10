package com.distribuida.service.impl;

import com.distribuida.dao.AutorRepository;
import com.distribuida.model.Autor;
import com.distribuida.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Autor findById(int id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.orElse(null);
    }

    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor update(int id, Autor autor) {
        Autor foundAutor = findById(id);

        if (foundAutor == null) {
            return null;
        }

        foundAutor.setName(autor.getName());
        foundAutor.setLastName(autor.getLastName());
        foundAutor.setCountry(autor.getCountry());
        foundAutor.setAddress(autor.getAddress());
        foundAutor.setPhone(autor.getPhone());
        foundAutor.setEmail(autor.getEmail());

        return autorRepository.save(foundAutor);
    }

    @Override
    public void delete(int id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        }
    }
}
