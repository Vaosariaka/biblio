package org.example.service;

import org.example.entity.Exemplaire;
import org.example.repository.ExemplaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    private final ExemplaireRepository exemplaireRepository;

    public ExemplaireService(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    public List<Exemplaire> findAll() {
        return exemplaireRepository.findAll();
    }

    public Optional<Exemplaire> findById(Integer id) {
        return exemplaireRepository.findById(id);
    }

    public Exemplaire saveOrUpdate(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    public void delete(Integer id) {
        exemplaireRepository.deleteById(id);
    }
}
