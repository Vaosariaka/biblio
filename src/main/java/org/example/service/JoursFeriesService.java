package org.example.service;

import org.example.entity.JoursFeries;
import org.example.repository.JoursFeriesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JoursFeriesService {

    private final JoursFeriesRepository joursFeriesRepository;

    public JoursFeriesService(JoursFeriesRepository joursFeriesRepository) {
        this.joursFeriesRepository = joursFeriesRepository;
    }

    public List<JoursFeries> findAll() {
        return joursFeriesRepository.findAll();
    }

    public Optional<JoursFeries> findById(LocalDate date) {
        return joursFeriesRepository.findById(date);
    }

    public JoursFeries saveOrUpdate(JoursFeries joursFeries) {
        return joursFeriesRepository.save(joursFeries);
    }

    public void delete(LocalDate date) {
        joursFeriesRepository.deleteById(date);
    }
}
