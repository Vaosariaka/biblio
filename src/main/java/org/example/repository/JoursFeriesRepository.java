package org.example.repository;

import org.example.entity.JoursFeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface JoursFeriesRepository extends JpaRepository<JoursFeries, LocalDate> {
}
