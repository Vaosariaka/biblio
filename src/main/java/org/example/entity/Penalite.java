package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "penalite")
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalite_id")
    private int penaliteId;

    @Column(name = "id_adherent", nullable = false)
    private int idAdherent;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "nbre_jours_sanction", nullable = false)
    private int nbreJoursSanction;

    @ManyToOne
    @JoinColumn(name = "id_adherent", insertable = false, updatable = false)
    private Adherent adherent;

    // Getters et Setters
    public int getPenaliteId() { return penaliteId; }
    public void setPenaliteId(int penaliteId) { this.penaliteId = penaliteId; }
    public int getIdAdherent() { return idAdherent; }
    public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public int getNbreJoursSanction() { return nbreJoursSanction; }
    public void setNbreJoursSanction(int nbreJoursSanction) { this.nbreJoursSanction = nbreJoursSanction; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
}
