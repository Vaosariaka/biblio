package org.example.controller;

import org.example.entity.Adherent;
import org.example.service.AdherentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adherents")
public class AdherentController {

    private final AdherentService adherentService;

    public AdherentController(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    @GetMapping
    public List<Adherent> getAllAdherents() {
        return adherentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable Integer id) {
        Optional<Adherent> adherent = adherentService.findById(id);
        return adherent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Adherent createAdherent(@RequestBody Adherent adherent) {
        return adherentService.saveOrUpdate(adherent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable Integer id, @RequestBody Adherent adherent) {
        Optional<Adherent> existing = adherentService.findById(id);
        if (existing.isPresent()) {
            adherent.setPersonneId(id);
            return ResponseEntity.ok(adherentService.saveOrUpdate(adherent));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdherent(@PathVariable Integer id) {
        if (adherentService.findById(id).isPresent()) {
            adherentService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
