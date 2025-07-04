package org.example.controller;

import org.example.entity.Reservation;
import org.example.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest reservationRequest) {
        String result = reservationService.createReservation(
                reservationRequest.getIdAdherent(),
                reservationRequest.getIdExemplaire(),
                reservationRequest.getDateReservation(),
                reservationRequest.getDatePret(),
                reservationRequest.getRegleAjustement()
        );
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody ReservationRequest reservationRequest) {
        Optional<Reservation> existing = reservationService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = new Reservation();
        reservation.setReservationId(id);
        reservation.setIdAdherent(reservationRequest.getIdAdherent());
        reservation.setIdExemplaire(reservationRequest.getIdExemplaire());
        reservation.setDateReservation(reservationRequest.getDateReservation());
        reservation.setDatePret(reservationRequest.getDatePret());

        try {
            Reservation updatedReservation = reservationService.saveOrUpdate(reservation, reservationRequest.getRegleAjustement());
            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        if (reservationService.findById(id).isPresent()) {
            reservationService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public static class ReservationRequest {
        private int idAdherent;
        private int idExemplaire;
        private LocalDate dateReservation;
        private LocalDate datePret;
        private String regleAjustement;

        // Getters et Setters
        public int getIdAdherent() { return idAdherent; }
        public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }
        public int getIdExemplaire() { return idExemplaire; }
        public void setIdExemplaire(int idExemplaire) { this.idExemplaire = idExemplaire; }
        public LocalDate getDateReservation() { return dateReservation; }
        public void setDateReservation(LocalDate dateReservation) { this.dateReservation = dateReservation; }
        public LocalDate getDatePret() { return datePret; }
        public void setDatePret(LocalDate datePret) { this.datePret = datePret; }
        public String getRegleAjustement() { return regleAjustement; }
        public void setRegleAjustement(String regleAjustement) { this.regleAjustement = regleAjustement; }
    }
}
