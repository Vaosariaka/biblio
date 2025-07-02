package org.example.repository;

import org.example.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotaRepository extends JpaRepository<Quota, Integer> {
}
