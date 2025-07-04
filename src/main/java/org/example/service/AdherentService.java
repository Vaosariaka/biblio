
package org.example.service;

import org.example.entity.Adherent;
import org.example.repository.AdherentRepository;
import org.springframework.stereotype.Service;
import org.example.entity.*;
import org.example.repository.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class AdherentService {
    private final AdherentRepository adherentRepository;
    private final PersonneRepository personneRepository;
   

    public AdherentService(AdherentRepository adherentRepository, PersonneRepository personneRepository) {
        this.adherentRepository = adherentRepository;
        this.personneRepository = personneRepository;
    }

    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> findById(Integer id) {
        return adherentRepository.findById(id);
    }

    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }

    public Map<String, Object> verifierAdherent(Integer adherentId) {
        Map<String, Object> result = new HashMap<>();
        Optional<Adherent> adherentOpt = adherentRepository.findById(adherentId);
        Optional<Personne> personneOpt = personneRepository.findById(adherentId);

        result.put("existe", adherentOpt.isPresent() && personneOpt.isPresent() && personneOpt.get().getType() == Personne.TypePersonne.adherent);
        result.put("abonnementValide", adherentOpt.isPresent() && !adherentOpt.get().getDateFin().isBefore(LocalDate.now()));

        return result;
    }

    public Map<String, Integer> analyserQuota(Integer adherentId) {
        Adherent adherent = adherentRepository.findById(adherentId)
                .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));

        Map<String, Integer> result = new HashMap<>();
        result.put("quotaPretsMax", adherent.getQuotaPrets());
        result.put("quotaProlongationsMax", adherent.getQuotaProlongations());
        return result;
    }
}
