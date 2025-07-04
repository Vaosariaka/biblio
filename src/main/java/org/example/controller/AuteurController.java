package org.example.controller;

import org.example.entity.Auteur;
import org.example.service.AuteurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    @GetMapping
    public String listAuteurs(Model model) {
        List<Auteur> auteurs = auteurService.findAll();
        model.addAttribute("auteurs", auteurs);
        return "auteur/list";
    }

    @GetMapping("/new")
    public String newAuteur(Model model) {
        model.addAttribute("auteur", new Auteur());
        return "auteur/form";
    }

    @PostMapping("/save")
    public String saveAuteur(@RequestParam String nom, Model model) {
        try {
            Auteur auteur = new Auteur();
            auteur.setNom(nom);
            auteurService.saveOrUpdate(auteur);
            return "redirect:/auteurs";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("auteur", new Auteur());
            return "auteur/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editAuteur(@PathVariable Integer id, Model model) {
        Auteur auteur = auteurService.findById(id).orElseThrow(() -> new IllegalArgumentException("Auteur introuvable"));
        model.addAttribute("auteur", auteur);
        return "auteur/form";
    }

    @PostMapping("/update/{id}")
    public String updateAuteur(@PathVariable Integer id, @RequestParam String nom, Model model) {
        try {
            Auteur auteur = auteurService.findById(id).orElseThrow(() -> new IllegalArgumentException("Auteur introuvable"));
            auteur.setNom(nom);
            auteurService.saveOrUpdate(auteur);
            return "redirect:/auteurs";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("auteur", auteurService.findById(id).orElse(new Auteur()));
            return "auteur/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAuteur(@PathVariable Integer id) {
        auteurService.delete(id);
        return "redirect:/auteurs";
    }
}
