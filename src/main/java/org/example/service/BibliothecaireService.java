package org.example.service;

import org.example.entity.Bibliothecaire;
import org.example.repository.BibliothecaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliothecaireService {

    private final BibliothecaireRepository bibliothecaireRepository;

    public BibliothecaireService(BibliothecaireRepository bibliothecaireRepository) {
        this.bibliothecaireRepository = bibliothecaireRepository;
    }

    public List<Bibliothecaire> findAll() {
        return bibliothecaireRepository.findAll();
    }

    public Optional<Bibliothecaire> findById(Integer id) {
        return bibliothecaireRepository.findById(id);
    }

    public Bibliothecaire saveOrUpdate(Bibliothecaire bibliothecaire) {
        return bibliothecaireRepository.save(bibliothecaire);
    }

    public void delete(Integer id) {
        bibliothecaireRepository.deleteById(id);
    }
}