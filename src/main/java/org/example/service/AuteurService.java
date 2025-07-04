package org.example.service;

import org.example.entity.Auteur;
import org.example.repository.AuteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService {

    private final AuteurRepository auteurRepository;

    public AuteurService(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    public List<Auteur> findAll() {
        return auteurRepository.findAll();
    }

    public Optional<Auteur> findById(Integer id) {
        return auteurRepository.findById(id);
    }

    public Auteur saveOrUpdate(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    public void delete(Integer id) {
        auteurRepository.deleteById(id);
    }
}
