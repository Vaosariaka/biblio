package org.example.service;

import org.example.entity.Penalite;
import org.example.repository.PenaliteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PenaliteService {

    private final PenaliteRepository penaliteRepository;

    public PenaliteService(PenaliteRepository penaliteRepository) {
        this.penaliteRepository = penaliteRepository;
    }

    public List<Penalite> findAll() {
        return penaliteRepository.findAll();
    }

    public Optional<Penalite> findById(Integer id) {
        return penaliteRepository.findById(id);
    }

    public Penalite saveOrUpdate(Penalite penalite) {
        return penaliteRepository.save(penalite);
    }

    public void delete(Integer id) {
        penaliteRepository.deleteById(id);
    }

    public boolean hasActivePenalite(int idAdherent) {
        List<Penalite> penalites = penaliteRepository.findActivePenalitesByAdherent(idAdherent, LocalDate.now());
        return !penalites.isEmpty();
    }

    public LocalDate getDateFinPenalite(Penalite penalite) {
        return penalite.getDateDebut().plusDays(penalite.getNbreJoursSanction());
    }
}
