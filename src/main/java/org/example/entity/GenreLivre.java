package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "genreLivre")
public class GenreLivre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    // Getters et Setters
    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}