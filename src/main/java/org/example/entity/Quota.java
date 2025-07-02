package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "quota")
public class Quota {
    @Id
    @Column(name = "id_adherent")
    private int idAdherent;

    @Column(name = "quota_prets_restants", nullable = false)
    private int quotaPretsRestants;

    @Column(name = "quota_prolongations_restants", nullable = false)
    private int quotaProlongationsRestants;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_adherent")
    private Adherent adherent;

    // Getters et Setters
    public int getIdAdherent() { return idAdherent; }
    public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }
    public int getQuotaPretsRestants() { return quotaPretsRestants; }
    public void setQuotaPretsRestants(int quotaPretsRestants) { this.quotaPretsRestants = quotaPretsRestants; }
    public int getQuotaProlongationsRestants() { return quotaProlongationsRestants; }
    public void setQuotaProlongationsRestants(int quotaProlongationsRestants) { this.quotaProlongationsRestants = quotaProlongationsRestants; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
}