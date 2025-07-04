package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Genre_id")
    private int GenreId;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    // Getters et Setters
    public int getGenreId() { return GenreId; }
    public void setGenreId(int GenreId) { this.GenreId = GenreId; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}