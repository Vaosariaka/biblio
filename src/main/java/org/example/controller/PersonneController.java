
package org.example.controller;

import org.example.entity.Personne;
import org.example.service.PersonneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

   @GetMapping("/list")
    public String listPersonnes(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; 
        }
        List<Personne> personnes = personneService.findAll();
        model.addAttribute("personnes", personnes);
        return "personne/list"; 
    }

    @GetMapping("/new")
    public String newPersonne(Model model) {
        model.addAttribute("personne", new Personne());
        model.addAttribute("personneTypes", Personne.TypePersonne.values());
        return "personne/form";
    }

    @PostMapping("/save")
    public String savePersonne(@RequestParam("nom") String nom,
                               @RequestParam("prenom") String prenom,
                               @RequestParam("dateNaissance") String dateNaissance,
                               @RequestParam("type") String type,
                               @RequestParam("login") String login,
                               @RequestParam("motDePasse") String motDePasse,
                               Model model) {
        try {
            Personne personne = new Personne();
            personne.setNom(nom);
            personne.setPrenom(prenom);
            personne.setDateNaissance(LocalDate.parse(dateNaissance));
            personne.setType(Personne.TypePersonne.valueOf(type));
            personne.setLogin(login);
            personne.setMotDePasse(motDePasse);
            personneService.saveOrUpdate(personne);
            return "redirect:/biblio/personnes";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("personne", new Personne());
            model.addAttribute("personneTypes", Personne.TypePersonne.values());
            return "personne/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editPersonne(@PathVariable Integer id, Model model) {
        Personne personne = personneService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));
        model.addAttribute("personne", personne);
        model.addAttribute("personneTypes", Personne.TypePersonne.values());
        return "personne/form";
    }

    @PostMapping("/update/{id}")
    public String updatePersonne(@PathVariable Integer id,
                                 @RequestParam("nom") String nom,
                                 @RequestParam("prenom") String prenom,
                                 @RequestParam("dateNaissance") String dateNaissance,
                                 @RequestParam("type") String type,
                                 @RequestParam("login") String login,
                                 @RequestParam("motDePasse") String motDePasse,
                                 Model model) {
        try {
            Personne personne = personneService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));
            personne.setNom(nom);
            personne.setPrenom(prenom);
            personne.setDateNaissance(LocalDate.parse(dateNaissance));
            personne.setType(Personne.TypePersonne.valueOf(type));
            personne.setLogin(login);
            personne.setMotDePasse(motDePasse);
            personneService.saveOrUpdate(personne);
            return "redirect:/biblio/personnes";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("personne", personneService.findById(id).orElse(new Personne()));
            model.addAttribute("personneTypes", Personne.TypePersonne.values());
            return "personne/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePersonne(@PathVariable Integer id) {
        personneService.delete(id);
        return "redirect:/biblio/personnes";
    }
}
