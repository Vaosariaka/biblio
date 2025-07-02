package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jours_feries")
public class JoursFeries {
    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    // Getters et Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}