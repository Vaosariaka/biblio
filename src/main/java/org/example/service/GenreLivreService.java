package org.example.service;

import org.example.entity.GenreLivre;
import org.example.repository.GenreLivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreLivreService {

    private final GenreLivreRepository genreLivreRepository;

    public GenreLivreService(GenreLivreRepository genreLivreRepository) {
        this.genreLivreRepository = genreLivreRepository;
    }

    public List<GenreLivre> findAll() {
        return genreLivreRepository.findAll();
    }

    public Optional<GenreLivre> findById(Integer id) {
        return genreLivreRepository.findById(id);
    }

    public GenreLivre saveOrUpdate(GenreLivre genreLivre) {
        return genreLivreRepository.save(genreLivre);
    }

    public void delete(Integer id) {
        genreLivreRepository.deleteById(id);
    }
}
