package com.distribuida.service.impl;

import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro findById(int id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.orElse(null);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(int id, Libro libro) {
        Libro foundLibro = findById(id);

        if (foundLibro == null) {
            return null;
        }

        foundLibro.setTitle(libro.getTitle());
        foundLibro.setEditorial(libro.getEditorial());
        foundLibro.setPages(libro.getPages());
        foundLibro.setEdition(libro.getEdition());
        foundLibro.setLanguage(libro.getLanguage());
        foundLibro.setPublicationDate(libro.getPublicationDate());
        foundLibro.setDescription(libro.getDescription());
        foundLibro.setCoverType(libro.getCoverType());
        foundLibro.setISBN(libro.getISBN());
        foundLibro.setCopies(libro.getCopies());
        foundLibro.setCover(libro.getCover());
        foundLibro.setPresentation(libro.getPresentation());
        foundLibro.setPrice(libro.getPrice());
        foundLibro.setCategoria(libro.getCategoria());
        foundLibro.setAutor(libro.getAutor());

        return libroRepository.save(foundLibro);
    }

    @Override
    public void delete(int id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        }
    }
}
