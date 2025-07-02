package org.example.controller;

import org.example.entity.Pret;
import org.example.service.PretService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prets")
public class PretController {

    private final PretService pretService;

    public PretController(PretService pretService) {
        this.pretService = pretService;
    }

    @GetMapping
    public List<Pret> getAllPrets() {
        return pretService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pret> getPretById(@PathVariable Integer id) {
        Optional<Pret> pret = pretService.findById(id);
        return pret.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createPret(@RequestBody PretRequest pretRequest) {
        String result = pretService.createPret(
                pretRequest.getIdAdherent(),
                pretRequest.getIdExemplaire(),
                pretRequest.getDatePret(),
                pretRequest.getDureePret(),
                pretRequest.getRegleAjustement()
        );
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/prolonger")
    public ResponseEntity<String> prolongerPret(@PathVariable Integer id, @RequestBody ProlongationRequest prolongationRequest) {
        String result = pretService.prolongerPret(id, prolongationRequest.getNouvelleDateRetour(), prolongationRequest.getRegleAjustement());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePret(@PathVariable Integer id) {
        if (pretService.findById(id).isPresent()) {
            pretService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public static class PretRequest {
        private int idAdherent;
        private int idExemplaire;
        private LocalDate datePret;
        private int dureePret;
        private String regleAjustement;

        // Getters et Setters
        public int getIdAdherent() { return idAdherent; }
        public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }
        public int getIdExemplaire() { return idExemplaire; }
        public void setIdExemplaire(int idExemplaire) { this.idExemplaire = idExemplaire; }
        public LocalDate getDatePret() { return datePret; }
        public void setDatePret(LocalDate datePret) { this.datePret = datePret; }
        public int getDureePret() { return dureePret; }
        public void setDureePret(int dureePret) { this.dureePret = dureePret; }
        public String getRegleAjustement() { return regleAjustement; }
        public void setRegleAjustement(String regleAjustement) { this.regleAjustement = regleAjustement; }
    }

    public static class ProlongationRequest {
        private LocalDate nouvelleDateRetour;
        private String regleAjustement;

        // Getters et Setters
        public LocalDate getNouvelleDateRetour() { return nouvelleDateRetour; }
        public void setNouvelleDateRetour(LocalDate nouvelleDateRetour) { this.nouvelleDateRetour = nouvelleDateRetour; }
        public String getRegleAjustement() { return regleAjustement; }
        public void setRegleAjustement(String regleAjustement) { this.regleAjustement = regleAjustement; }
    }
}
