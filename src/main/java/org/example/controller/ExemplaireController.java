package org.example.controller;

import org.example.entity.Exemplaire;
import org.example.service.ExemplaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exemplaires")
public class ExemplaireController {

    private final ExemplaireService exemplaireService;

    public ExemplaireController(ExemplaireService exemplaireService) {
        this.exemplaireService = exemplaireService;
    }

    @GetMapping
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exemplaire> getExemplaireById(@PathVariable Integer id) {
        Optional<Exemplaire> exemplaire = exemplaireService.findById(id);
        return exemplaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exemplaire createExemplaire(@RequestBody Exemplaire exemplaire) {
        return exemplaireService.saveOrUpdate(exemplaire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exemplaire> updateExemplaire(@PathVariable Integer id, @RequestBody Exemplaire exemplaire) {
        Optional<Exemplaire> existing = exemplaireService.findById(id);
        if (existing.isPresent()) {
            exemplaire.setExemplaireId(id);
            return ResponseEntity.ok(exemplaireService.saveOrUpdate(exemplaire));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExemplaire(@PathVariable Integer id) {
        if (exemplaireService.findById(id).isPresent()) {
            exemplaireService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
