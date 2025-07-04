package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exemplaire_id")
    private int exemplaireId;

    @Column(name = "id_livre", nullable = false)
    private int idLivre;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutExemplaire statut;

    @ManyToOne
    @JoinColumn(name = "id_livre", insertable = false, updatable = false)
    private Livre livre;

    public enum StatutExemplaire {
        Disponible, Emprunte, Reserve
    }

    // Getters et Setters
    public int getExemplaireId() { return exemplaireId; }
    public void setExemplaireId(int exemplaireId) { this.exemplaireId = exemplaireId; }
    public int getIdLivre() { return idLivre; }
    public void setIdLivre(int idLivre) { this.idLivre = idLivre; }
    public StatutExemplaire getStatut() { return statut; }
    public void setStatut(StatutExemplaire statut) { this.statut = statut; }
    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}