package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pret")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pret_id")
    private int pretId;

    @Column(name = "id_adherent", nullable = false)
    private int idAdherent;

    @Column(name = "id_exemplaire", nullable = false)
    private int idExemplaire;

    @Column(name = "date_pret", nullable = false)
    private LocalDate datePret;

    @Column(name = "date_retour", nullable = false)
    private LocalDate dateRetour;

    @Column(name = "nb_prolongations", nullable = false)
    private int nbProlongations;

    @Column(name = "regle_ajustement", nullable = false)
    @Enumerated(EnumType.STRING)
    private RegleAjustement regleAjustement;

    @ManyToOne
    @JoinColumn(name = "id_adherent", insertable = false, updatable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", insertable = false, updatable = false)
    private Exemplaire exemplaire;

    public enum RegleAjustement {
        avant, apres
    }

    // Getters et Setters
    public int getPretId() { return pretId; }
    public void setPretId(int pretId) { this.pretId = pretId; }
    public int getIdAdherent() { return idAdherent; }
    public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }
    public int getIdExemplaire() { return idExemplaire; }
    public void setIdExemplaire(int idExemplaire) { this.idExemplaire = idExemplaire; }
    public LocalDate getDatePret() { return datePret; }
    public void setDatePret(LocalDate datePret) { this.datePret = datePret; }
    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }
    public int getNbProlongations() { return nbProlongations; }
    public void setNbProlongations(int nbProlongations) { this.nbProlongations = nbProlongations; }
    public RegleAjustement getRegleAjustement() { return regleAjustement; }
    public void setRegleAjustement(RegleAjustement regleAjustement) { this.regleAjustement = regleAjustement; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }
}