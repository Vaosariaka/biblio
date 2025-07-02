package org.example.controller;

import org.example.entity.Personne;
import org.example.service.PersonneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Integer id) {
        Optional<Personne> personne = personneService.findById(id);
        return personne.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneService.saveOrUpdate(personne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Integer id, @RequestBody Personne personne) {
        Optional<Personne> existing = personneService.findById(id);
        if (existing.isPresent()) {
            personne.setPersonneId(id);
            return ResponseEntity.ok(personneService.saveOrUpdate(personne));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Integer id) {
        if (personneService.findById(id).isPresent()) {
            personneService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
