package org.example.repository;

import org.example.entity.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    @Query(value = "SELECT p FROM Penalite p WHERE p.idAdherent = :idAdherent AND :currentDate < p.dateDebut + INTERVAL '1 day' * p.nbreJoursSanction")
    List<Penalite> findActivePenalitesByAdherent(@Param("idAdherent") int idAdherent, @Param("currentDate") LocalDate currentDate);
}
