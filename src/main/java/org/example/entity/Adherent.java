package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adherent")
public class Adherent {
    @Id
    @Column(name = "personne_id")
    private int personneId;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false)
    private CategorieAdherent categorie;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "quota_prets", nullable = false)
    private int quotaPrets;

    @Column(name = "quota_prolongations", nullable = false)
    private int quotaProlongations;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personne_id")
    private Personne personne;

    public enum CategorieAdherent {
        etudiant, professionnel, professeur
    }

    // Getters et Setters
    public int getPersonneId() { return personneId; }
    public void setPersonneId(int personneId) { this.personneId = personneId; }
    public CategorieAdherent getCategorie() { return categorie; }
    public void setCategorie(CategorieAdherent categorie) { this.categorie = categorie; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public int getQuotaPrets() { return quotaPrets; }
    public void setQuotaPrets(int quotaPrets) { this.quotaPrets = quotaPrets; }
    public int getQuotaProlongations() { return quotaProlongations; }
    public void setQuotaProlongations(int quotaProlongations) { this.quotaProlongations = quotaProlongations; }
    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }
}