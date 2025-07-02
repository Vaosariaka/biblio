package org.example.controller;

import org.example.entity.Livre;
import org.example.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Integer id) {
        Optional<Livre> livre = livreService.findById(id);
        return livre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.saveOrUpdate(livre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Integer id, @RequestBody Livre livre) {
        Optional<Livre> existing = livreService.findById(id);
        if (existing.isPresent()) {
            livre.setLivreId(id);
            return ResponseEntity.ok(livreService.saveOrUpdate(livre));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Integer id) {
        if (livreService.findById(id).isPresent()) {
            livreService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
