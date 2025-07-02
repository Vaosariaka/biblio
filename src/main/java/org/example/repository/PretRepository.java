package org.example.repository;

import org.example.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PretRepository extends JpaRepository<Pret, Integer> {
}
