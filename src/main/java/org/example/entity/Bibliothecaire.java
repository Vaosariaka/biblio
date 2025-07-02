package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "bibliothecaire")
public class Bibliothecaire {
    @Id
    @Column(name = "personne_id")
    private int personneId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personne_id")
    private Personne personne;

    // Getters et Setters
    public int getPersonneId() { return personneId; }
    public void setPersonneId(int personneId) { this.personneId = personneId; }
    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }
}