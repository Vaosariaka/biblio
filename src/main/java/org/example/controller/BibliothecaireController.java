package org.example.controller;

import org.example.entity.Bibliothecaire;
import org.example.service.BibliothecaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bibliothecaires")
public class BibliothecaireController {

    private final BibliothecaireService bibliothecaireService;

    public BibliothecaireController(BibliothecaireService bibliothecaireService) {
        this.bibliothecaireService = bibliothecaireService;
    }

    @GetMapping
    public List<Bibliothecaire> getAllBibliothecaires() {
        return bibliothecaireService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bibliothecaire> getBibliothecaireById(@PathVariable Integer id) {
        Optional<Bibliothecaire> bibliothecaire = bibliothecaireService.findById(id);
        return bibliothecaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bibliothecaire createBibliothecaire(@RequestBody Bibliothecaire bibliothecaire) {
        return bibliothecaireService.saveOrUpdate(bibliothecaire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bibliothecaire> updateBibliothecaire(@PathVariable Integer id, @RequestBody Bibliothecaire bibliothecaire) {
        Optional<Bibliothecaire> existing = bibliothecaireService.findById(id);
        if (existing.isPresent()) {
            bibliothecaire.setPersonneId(id);
            return ResponseEntity.ok(bibliothecaireService.saveOrUpdate(bibliothecaire));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBibliothecaire(@PathVariable Integer id) {
        if (bibliothecaireService.findById(id).isPresent()) {
            bibliothecaireService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
 