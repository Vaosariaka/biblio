package org.example.controller;

import org.example.entity.*;
import org.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional; 

@Controller
@RequestMapping("/adherents")
public class AdherentController {
    private final AdherentService adherentService;
    private final PersonneService personneService;

    public AdherentController(AdherentService adherentService, PersonneService personneService) {
        this.adherentService = adherentService;
        this.personneService = personneService;
    }

    @GetMapping
    public String listAdherents(Model model) {
        List<Adherent> adherents = adherentService.findAll();
        model.addAttribute("adherents", adherents);
        return "adherent/list";
    }

    @GetMapping("/new")
    public String newAdherent(Model model) {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("personnes", personneService.findAll());
        return "adherent/form";
    }

    @PostMapping("/save")
    public String saveAdherent(@RequestParam Integer personneId, @RequestParam String categorie,
                               @RequestParam String dateDebut, @RequestParam String dateFin,
                               @RequestParam Integer quotaPrets, @RequestParam Integer quotaProlongations,
                               Model model) {
        try {
            // Validation des paramètres
            if (personneId == null || quotaPrets == null || quotaProlongations == null) {
                model.addAttribute("message", "Erreur : Tous les champs numériques sont requis.");
                model.addAttribute("adherent", new Adherent());
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            if (!Arrays.asList("etudiant", "professionnel", "professeur").contains(categorie.toLowerCase())) {
                model.addAttribute("message", "Erreur : Catégorie invalide (etudiant, professionnel, professeur).");
                model.addAttribute("adherent", new Adherent());
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            LocalDate parsedDateDebut;
            LocalDate parsedDateFin;
            try {
                parsedDateDebut = LocalDate.parse(dateDebut);
                parsedDateFin = LocalDate.parse(dateFin);
            } catch (DateTimeParseException e) {
                model.addAttribute("message", "Erreur : Format de date invalide (attendu : yyyy-MM-dd).");
                model.addAttribute("adherent", new Adherent());
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            if (parsedDateFin.isBefore(parsedDateDebut)) {
                model.addAttribute("message", "Erreur : La date de fin doit être postérieure à la date de début.");
                model.addAttribute("adherent", new Adherent());
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            if (quotaPrets < 0 || quotaProlongations < 0) {
                model.addAttribute("message", "Erreur : Les quotas ne peuvent pas être négatifs.");
                model.addAttribute("adherent", new Adherent());
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }

            // Vérifier si la personne existe et est de type adherent
            Optional<Personne> personneOpt = personneService.findById(personneId);
            if (personneOpt.isEmpty() || personneOpt.get().getType() != Personne.TypePersonne.adherent) {
                model.addAttribute("message", "Erreur : Personne inexistante ou type incorrect.");
                model.addAttribute("adherent", new Adherent());
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }

            Adherent adherent = new Adherent();
            adherent.setPersonneId(personneId);
            adherent.setCategorie(Adherent.CategorieAdherent.valueOf(categorie));
            adherent.setDateDebut(parsedDateDebut);
            adherent.setDateFin(parsedDateFin);
            adherent.setQuotaPrets(quotaPrets);
            adherent.setQuotaProlongations(quotaProlongations);
            adherent.setPersonne(personneOpt.get());

            adherentService.save(adherent);
            return "redirect:/adherents";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("adherent", new Adherent());
            model.addAttribute("personnes", personneService.findAll());
            return "adherent/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editAdherent(@PathVariable Integer id, Model model) {
        Adherent adherent = adherentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));
        model.addAttribute("adherent", adherent);
        model.addAttribute("personnes", personneService.findAll());
        return "adherent/form";
    }

    @PostMapping("/update/{id}")
    public String updateAdherent(@PathVariable Integer id, @RequestParam Integer personneId,
                                @RequestParam String categorie, @RequestParam String dateDebut,
                                @RequestParam String dateFin, @RequestParam Integer quotaPrets,
                                @RequestParam Integer quotaProlongations, Model model) {
        try {
            // Validation des paramètres
            if (personneId == null || quotaPrets == null || quotaProlongations == null) {
                model.addAttribute("message", "Erreur : Tous les champs numériques sont requis.");
                model.addAttribute("adherent", adherentService.findById(id).orElse(new Adherent()));
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            if (!Arrays.asList("etudiant", "professionnel", "professeur").contains(categorie.toLowerCase())) {
                model.addAttribute("message", "Erreur : Catégorie invalide (etudiant, professionnel, professeur).");
                model.addAttribute("adherent", adherentService.findById(id).orElse(new Adherent()));
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            LocalDate parsedDateDebut;
            LocalDate parsedDateFin;
            try {
                parsedDateDebut = LocalDate.parse(dateDebut);
                parsedDateFin = LocalDate.parse(dateFin);
            } catch (DateTimeParseException e) {
                model.addAttribute("message", "Erreur : Format de date invalide (attendu : yyyy-MM-dd).");
                model.addAttribute("adherent", adherentService.findById(id).orElse(new Adherent()));
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            if (parsedDateFin.isBefore(parsedDateDebut)) {
                model.addAttribute("message", "Erreur : La date de fin doit être postérieure à la date de début.");
                model.addAttribute("adherent", adherentService.findById(id).orElse(new Adherent()));
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }
            if (quotaPrets < 0 || quotaProlongations < 0) {
                model.addAttribute("message", "Erreur : Les quotas ne peuvent pas être négatifs.");
                model.addAttribute("adherent", adherentService.findById(id).orElse(new Adherent()));
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }

            Adherent adherent = adherentService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));
            Optional<Personne> personneOpt = personneService.findById(personneId);
            if (personneOpt.isEmpty() || personneOpt.get().getType() != Personne.TypePersonne.adherent) {
                model.addAttribute("message", "Erreur : Personne inexistante ou type incorrect.");
                model.addAttribute("adherent", adherent);
                model.addAttribute("personnes", personneService.findAll());
                return "adherent/form";
            }

            adherent.setPersonneId(personneId);
            adherent.setCategorie(Adherent.CategorieAdherent.valueOf(categorie));
            adherent.setDateDebut(parsedDateDebut);
            adherent.setDateFin(parsedDateFin);
            adherent.setQuotaPrets(quotaPrets);
            adherent.setQuotaProlongations(quotaProlongations);
            adherent.setPersonne(personneOpt.get());

            adherentService.save(adherent);
            return "redirect:/adherents";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("adherent", adherentService.findById(id).orElse(new Adherent()));
            model.addAttribute("personnes", personneService.findAll());
            return "adherent/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAdherent(@PathVariable Integer id) {
        adherentService.delete(id);
        return "redirect:/adherents";
    }
}