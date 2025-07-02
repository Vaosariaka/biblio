package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livre_id")
    private int livreId;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "id_genre", nullable = false)
    private int idGenre;

    @Column(name = "id_auteur", nullable = false)
    private int idAuteur;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "nombre_exemplaires", nullable = false)
    private int nombreExemplaires;

    @ManyToOne
    @JoinColumn(name = "id_genre", insertable = false, updatable = false)
    private GenreLivre genre;

    @ManyToOne
    @JoinColumn(name = "id_auteur", insertable = false, updatable = false)
    private Auteur auteur;

    // Getters et Setters
    public int getLivreId() { return livreId; }
    public void setLivreId(int livreId) { this.livreId = livreId; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public int getIdGenre() { return idGenre; }
    public void setIdGenre(int idGenre) { this.idGenre = idGenre; }
    public int getIdAuteur() { return idAuteur; }
    public void setIdAuteur(int idAuteur) { this.idAuteur = idAuteur; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public int getNombreExemplaires() { return nombreExemplaires; }
    public void setNombreExemplaires(int nombreExemplaires) { this.nombreExemplaires = nombreExemplaires; }
    public GenreLivre getGenre() { return genre; }
    public void setGenre(GenreLivre genre) { this.genre = genre; }
    public Auteur getAuteur() { return auteur; }
    public void setAuteur(Auteur auteur) { this.auteur = auteur; }
}