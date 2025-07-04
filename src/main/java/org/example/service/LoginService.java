package org.example.service;

import org.example.entity.Personne;
import org.example.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private PersonneRepository personneRepository;

    public Optional<Personne> login(String login, String motDePasse) {
        Optional<Personne> personne = personneRepository.findByLogin(login);
        if (personne.isPresent() && personne.get().getMotDePasse().equals(motDePasse)) {
            return personne;
        }
        return Optional.empty();
    }
}