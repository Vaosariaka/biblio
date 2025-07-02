package org.example.service;

import org.example.entity.Quota;
import org.example.repository.QuotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotaService {

    private final QuotaRepository quotaRepository;

    public QuotaService(QuotaRepository quotaRepository) {
        this.quotaRepository = quotaRepository;
    }

    public List<Quota> findAll() {
        return quotaRepository.findAll();
    }

    public Optional<Quota> findById(Integer id) {
        return quotaRepository.findById(id);
    }

    public Quota saveOrUpdate(Quota quota) {
        return quotaRepository.save(quota);
    }

    public void delete(Integer id) {
        quotaRepository.deleteById(id);
    }
}
