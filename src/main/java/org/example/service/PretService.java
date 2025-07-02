package org.example.service;

import org.example.entity.*;
import org.example.repository.*;
import org.example.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    private final PretRepository pretRepository;
    private final AdherentRepository adherentRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final PenaliteRepository penaliteRepository;
    private final ReservationRepository reservationRepository;
    private final DateUtils dateUtils;

    public PretService(PretRepository pretRepository, AdherentRepository adherentRepository,
                      ExemplaireRepository exemplaireRepository, PenaliteRepository penaliteRepository,
                      ReservationRepository reservationRepository, DateUtils dateUtils) {
        this.pretRepository = pretRepository;
        this.adherentRepository = adherentRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.penaliteRepository = penaliteRepository;
        this.reservationRepository = reservationRepository;
        this.dateUtils = dateUtils;
    }

    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    public Optional<Pret> findById(Integer id) {
        return pretRepository.findById(id);
    }

    public String createPret(int idAdherent, int idExemplaire, LocalDate datePret, int dureePret, String regleAjustement) {
        try {
            // Vérifier l'adhérent
            Optional<Adherent> adherentOpt = adherentRepository.findById(idAdherent);
            if (adherentOpt.isEmpty() || adherentOpt.get().getDateFin().isBefore(LocalDate.now())) {
                return "Erreur : Adhérent inactif ou inexistant.";
            }
            Adherent adherent = adherentOpt.get();

            // Vérifier le quota
            if (adherent.getQuotaPrets() <= 0) {
                return "Erreur : Quota de prêts atteint.";
            }

            // Vérifier l'exemplaire
            Optional<Exemplaire> exemplaireOpt = exemplaireRepository.findById(idExemplaire);
            if (exemplaireOpt.isEmpty() || exemplaireOpt.get().getStatut() != Exemplaire.StatutExemplaire.Disponible) {
                return "Erreur : Exemplaire indisponible.";
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

            // Calculer et ajuster la date de retour
            LocalDate dateRetour = dateUtils.adjustReturnDate(datePret.plusDays(dureePret), regleAjustement);

            // Créer le prêt
            Pret pret = new Pret();
            pret.setIdAdherent(idAdherent);
            pret.setIdExemplaire(idExemplaire);
            pret.setDatePret(datePret);
            pret.setDateRetour(dateRetour);
            pret.setNbProlongations(0);

            // Mettre à jour l'exemplaire et le quota
            exemplaire.setStatut(Exemplaire.StatutExemplaire.Emprunte);
            adherent.setQuotaPrets(adherent.getQuotaPrets() - 1);

            pretRepository.save(pret);
            exemplaireRepository.save(exemplaire);
            adherentRepository.save(adherent);

            return "Prêt enregistré avec succès.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String prolongerPret(int pretId, LocalDate nouvelleDateRetour, String regleAjustement) {
        try {
            Optional<Pret> pretOpt = pretRepository.findById(pretId);
            if (pretOpt.isEmpty()) {
                return "Erreur : Prêt inexistant.";
            }
            Pret pret = pretOpt.get();

            Optional<Adherent> adherentOpt = adherentRepository.findById(pret.getIdAdherent());
            if (adherentOpt.isEmpty() || adherentOpt.get().getDateFin().isBefore(LocalDate.now())) {
                return "Erreur : Adhérent inactif.";
            }
            Adherent adherent = adherentOpt.get();

            if (adherent.getQuotaProlongations() <= 0) {
                return "Erreur : Quota de prolongations atteint.";
            }

            // Vérifier s'il y a une réservation conflictuelle
            List<Reservation> reservations = reservationRepository.findAll();
            for (Reservation reservation : reservations) {
                if (reservation.getIdExemplaire() == pret.getIdExemplaire() && reservation.getDatePret() != null && reservation.getDatePret().isBefore(nouvelleDateRetour)) {
                    return "Erreur : Exemplaire réservé par un autre adhérent.";
                }
            }

            // Ajuster la date de retour
            LocalDate adjustedDateRetour = dateUtils.adjustReturnDate(nouvelleDateRetour, regleAjustement);

            pret.setDateRetour(adjustedDateRetour);
            pret.setNbProlongations(pret.getNbProlongations() + 1);
            adherent.setQuotaProlongations(adherent.getQuotaProlongations() - 1);

            pretRepository.save(pret);
            adherentRepository.save(adherent);

            return "Prolongation enregistrée avec succès.";
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
        pretRepository.deleteById(id);
    }
}
