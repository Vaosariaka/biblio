package org.example.service;

import org.example.entity.Adherent;
import org.example.repository.AdherentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    private final AdherentRepository adherentRepository;

    public AdherentService(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> findById(Integer id) {
        return adherentRepository.findById(id);
    }

    public Adherent saveOrUpdate(Adherent adherent) {
        if (adherent.getDateFin() != null && adherent.getDateFin().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Adhésion expirée.");
        }
        return adherentRepository.save(adherent);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }
}