package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "auteur")
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auteur_id")
    private int auteurId;

    @Column(name = "nom", nullable = false)
    private String nom;

    public int getAuteurId() { return auteurId; }
    public void setAuteurId(int auteurId) { this.auteurId = auteurId; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}