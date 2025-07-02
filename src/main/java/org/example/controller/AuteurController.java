package org.example.controller;

import org.example.entity.Auteur;
import org.example.service.AuteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return auteurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Integer id) {
        Optional<Auteur> auteur = auteurService.findById(id);
        return auteur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return auteurService.saveOrUpdate(auteur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Integer id, @RequestBody Auteur auteur) {
        Optional<Auteur> existing = auteurService.findById(id);
        if (existing.isPresent()) {
            auteur.setAuteurId(id);
            return ResponseEntity.ok(auteurService.saveOrUpdate(auteur));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Integer id) {
        if (auteurService.findById(id).isPresent()) {
            auteurService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
