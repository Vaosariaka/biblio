package org.example.controller;

import org.example.entity.Bibliothecaire;
import org.example.service.BibliothecaireService;
import org.example.service.PersonneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bibliothecaires")
public class BibliothecaireController {

    private final BibliothecaireService bibliothecaireService;
    private final PersonneService personneService;

    public BibliothecaireController(BibliothecaireService bibliothecaireService, PersonneService personneService) {
        this.bibliothecaireService = bibliothecaireService;
        this.personneService = personneService;
    }

    @GetMapping
    public String listBibliothecaires(Model model) {
        List<Bibliothecaire> bibliothecaires = bibliothecaireService.findAll();
        model.addAttribute("bibliothecaires", bibliothecaires);
        return "bibliothecaire/list";
    }

    @GetMapping("/new")
    public String newBibliothecaire(Model model) {
        model.addAttribute("bibliothecaire", new Bibliothecaire());
        model.addAttribute("personnes", personneService.findAll());
        return "bibliothecaire/form";
    }

    @PostMapping("/save")
    public String saveBibliothecaire(@RequestParam Integer personneId, Model model) {
        try {
            Bibliothecaire bibliothecaire = new Bibliothecaire();
            bibliothecaire.setPersonneId(personneId);
            bibliothecaireService.saveOrUpdate(bibliothecaire);
            return "redirect:/bibliothecaires";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("bibliothecaire", new Bibliothecaire());
            model.addAttribute("personnes", personneService.findAll());
            return "bibliothecaire/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBibliothecaire(@PathVariable Integer id, Model model) {
        Bibliothecaire bibliothecaire = bibliothecaireService.findById(id).orElseThrow(() -> new IllegalArgumentException("Bibliothécaire introuvable"));
        model.addAttribute("bibliothecaire", bibliothecaire);
        model.addAttribute("personnes", personneService.findAll());
        return "bibliothecaire/form";
    }

    @PostMapping("/update/{id}")
    public String updateBibliothecaire(@PathVariable Integer id, @RequestParam Integer personneId, Model model) {
        try {
            Bibliothecaire bibliothecaire = bibliothecaireService.findById(id).orElseThrow(() -> new IllegalArgumentException("Bibliothécaire introuvable"));
            bibliothecaire.setPersonneId(personneId);
            bibliothecaireService.saveOrUpdate(bibliothecaire);
            return "redirect:/bibliothecaires";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("bibliothecaire", bibliothecaireService.findById(id).orElse(new Bibliothecaire()));
            model.addAttribute("personnes", personneService.findAll());
            return "bibliothecaire/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBibliothecaire(@PathVariable Integer id) {
        bibliothecaireService.delete(id);
        return "redirect:/bibliothecaires";
    }
}
