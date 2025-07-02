package org.example.service;

import org.example.entity.*;
import org.example.repository.*;
import org.example.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AdherentRepository adherentRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final PenaliteRepository penaliteRepository;
    private final DateUtils dateUtils;

    public ReservationService(ReservationRepository reservationRepository, AdherentRepository adherentRepository,
                             ExemplaireRepository exemplaireRepository, PenaliteRepository penaliteRepository,
                             DateUtils dateUtils) {
        this.reservationRepository = reservationRepository;
        this.adherentRepository = adherentRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.penaliteRepository = penaliteRepository;
        this.dateUtils = dateUtils;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Integer id) {
        return reservationRepository.findById(id);
    }

    public String createReservation(int idAdherent, int idExemplaire, LocalDate dateReservation, LocalDate datePret, String regleAjustement) {
        try {
            // Vérifier l'adhérent
            Optional<Adherent> adherentOpt = adherentRepository.findById(idAdherent);
            if (adherentOpt.isEmpty() || adherentOpt.get().getDateFin().isBefore(LocalDate.now())) {
                return "Erreur : Adhérent inactif ou inexistant.";
            }
            Adherent adherent = adherentOpt.get();

            // Vérifier le quota
            if (adherent.getQuotaPrets() <= 0) {
                return "Erreur : Quota de réservations atteint.";
            }

            // Vérifier l'exemplaire
            Optional<Exemplaire> exemplaireOpt = exemplaireRepository.findById(idExemplaire);
            if (exemplaireOpt.isEmpty()) {
                return "Erreur : Exemplaire inexistant.";
            }
            Exemplaire exemplaire = exemplaireOpt.get();

            // Vérifier les pénalités
            List<Penalite> penalites = penaliteRepository.findActivePenalitesByAdherent(idAdherent, LocalDate.now());
            if (!penalites.isEmpty()) {
                return "Erreur : Adhérent sanctionné.";
            }

            // Vérifier l'accessibilité du livre
            Livre livre = exemplaire.getLivre();
            if (!isLivreAccessible(adherent, livre)) {
                return "Erreur : Livre non accessible pour ce profil.";
            }

            // Ajuster la date de prêt
            LocalDate adjustedDatePret = datePret != null ? dateUtils.adjustReturnDate(datePret, regleAjustement) : null;

            // Créer la réservation
            Reservation reservation = new Reservation();
            reservation.setIdAdherent(idAdherent);
            reservation.setIdExemplaire(idExemplaire);
            reservation.setDateReservation(dateReservation);
            reservation.setDatePret(adjustedDatePret);

            // Mettre à jour l'exemplaire et le quota
            exemplaire.setStatut(Exemplaire.StatutExemplaire.Reserve);
            adherent.setQuotaPrets(adherent.getQuotaPrets() - 1);

            reservationRepository.save(reservation);
            exemplaireRepository.save(exemplaire);
            adherentRepository.save(adherent);

            return "Réservation enregistrée avec succès.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    private boolean isLivreAccessible(Adherent adherent, Livre livre) {
        String categorieAdherent = adherent.getCategorie().toString();
        String categorieLivre = livre.getCategorie();
        LocalDate dateNaissance = adherent.getPersonne().getDateNaissance();
        int age = LocalDate.now().getYear() - dateNaissance.getYear();

        if ("erotique".equalsIgnoreCase(categorieLivre) && age < 18) {
            return false;
        }
        return true;
    }

    public void delete(Integer id) {
        reservationRepository.deleteById(id);
    }
}
