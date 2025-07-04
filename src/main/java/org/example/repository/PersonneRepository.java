package org.example.repository;

import org.example.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    Optional<Personne> findByLogin(String login);
}