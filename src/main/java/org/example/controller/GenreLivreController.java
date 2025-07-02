package org.example.controller;

import org.example.entity.GenreLivre;
import org.example.service.GenreLivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
public class GenreLivreController {

    private final GenreLivreService genreLivreService;

    public GenreLivreController(GenreLivreService genreLivreService) {
        this.genreLivreService = genreLivreService;
    }

    @GetMapping
    public List<GenreLivre> getAllGenres() {
        return genreLivreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreLivre> getGenreById(@PathVariable Integer id) {
        Optional<GenreLivre> genre = genreLivreService.findById(id);
        return genre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GenreLivre createGenre(@RequestBody GenreLivre genre) {
        return genreLivreService.saveOrUpdate(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreLivre> updateGenre(@PathVariable Integer id, @RequestBody GenreLivre genre) {
        Optional<GenreLivre> existing = genreLivreService.findById(id);
        if (existing.isPresent()) {
            genre.setGenreId(id);
            return ResponseEntity.ok(genreLivreService.saveOrUpdate(genre));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        if (genreLivreService.findById(id).isPresent()) {
            genreLivreService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
