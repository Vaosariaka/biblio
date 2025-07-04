package org.example.controller;

import org.example.entity.Exemplaire;
import org.example.entity.Livre;
import org.example.service.ExemplaireService;
import org.example.service.LivreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/exemplaires")
public class ExemplaireController {

    private final ExemplaireService exemplaireService;
    private final LivreService livreService;

    public ExemplaireController(ExemplaireService exemplaireService, LivreService livreService) {
        this.exemplaireService = exemplaireService;
        this.livreService = livreService;
    }

    @GetMapping
    public String listExemplaires(Model model) {
        List<Exemplaire> exemplaires = exemplaireService.findAll();
        model.addAttribute("exemplaires", exemplaires);
        return "exemplaire/list";
    }

    @GetMapping("/new")
    public String newExemplaire(Model model) {
        model.addAttribute("exemplaire", new Exemplaire());
        model.addAttribute("livres", livreService.findAll());
        return "exemplaire/form";
    }

    @PostMapping("/save")
    public String saveExemplaire(@RequestParam Integer idLivre, @RequestParam String statut, Model model) {
        try {
            Exemplaire exemplaire = new Exemplaire();
            exemplaire.setIdLivre(idLivre);
            exemplaire.setStatut(Exemplaire.StatutExemplaire.valueOf(statut));
            exemplaireService.saveOrUpdate(exemplaire);
            return "redirect:/exemplaires";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("exemplaire", new Exemplaire());
            model.addAttribute("livres", livreService.findAll());
            return "exemplaire/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editExemplaire(@PathVariable Integer id, Model model) {
        Exemplaire exemplaire = exemplaireService.findById(id).orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));
        model.addAttribute("exemplaire", exemplaire);
        model.addAttribute("livres", livreService.findAll());
        return "exemplaire/form";
    }

    @PostMapping("/update/{id}")
    public String updateExemplaire(@PathVariable Integer id, @RequestParam Integer idLivre, @RequestParam String statut, Model model) {
        try {
            Exemplaire exemplaire = exemplaireService.findById(id).orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));
            exemplaire.setIdLivre(idLivre);
            exemplaire.setStatut(Exemplaire.StatutExemplaire.valueOf(statut));
            exemplaireService.saveOrUpdate(exemplaire);
            return "redirect:/exemplaires";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("exemplaire", exemplaireService.findById(id).orElse(new Exemplaire()));
            model.addAttribute("livres", livreService.findAll());
            return "exemplaire/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteExemplaire(@PathVariable Integer id) {
        exemplaireService.delete(id);
        return "redirect:/exemplaires";
    }
}
