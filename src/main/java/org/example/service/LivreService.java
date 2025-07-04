package org.example.service;

import org.example.entity.Livre;
import org.example.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    public Optional<Livre> findById(Integer id) {
        return livreRepository.findById(id);
    }

    public Livre saveorUpdate(Livre livre) {
        return livreRepository.save(livre);
    }

    public void delete(Integer id) {
        livreRepository.deleteById(id);
    }
}