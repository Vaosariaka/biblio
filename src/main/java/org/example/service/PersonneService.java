package org.example.service;

import org.example.entity.Personne;
import org.example.repository.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Optional<Personne> findById(Integer id) {
        return personneRepository.findById(id);
    }

    public Personne saveOrUpdate(Personne personne) {
        return personneRepository.save(personne);
    }

    public void delete(Integer id) {
        personneRepository.deleteById(id);
    }
}
