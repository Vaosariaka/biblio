package org.example.controller;

import org.example.entity.Genre;
import org.example.entity.Livre;
import org.example.service.AuteurService;
import org.example.service.GenreService;
import org.example.service.LivreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/livres")
public class LivreController {

    private final LivreService livreService;
    private final GenreService genreService;
    private final AuteurService auteurService;

    public LivreController(LivreService livreService, GenreService genreService, AuteurService auteurService) {
        this.livreService = livreService;
        this.genreService = genreService;
        this.auteurService = auteurService;
    }

    @GetMapping
    public String listLivres(Model model) {
        List<Livre> livres = livreService.findAll();
        model.addAttribute("livres", livres);
        return "livre/list";
    }

    @GetMapping("/new")
    public String newLivre(Model model) {
        model.addAttribute("livre", new Livre());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("auteurs", auteurService.findAll());
        model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
        return "livre/form";
    }

    @PostMapping("/save")
    public String saveLivre(@RequestParam String titre, @RequestParam Integer idGenre,
                            @RequestParam Integer idAuteur, @RequestParam String categorie,
                            @RequestParam Integer nombreExemplaires, Model model) {
        try {
            // Validation des paramètres
            if (titre == null || titre.trim().isEmpty()) {
                model.addAttribute("message", "Erreur : Le titre est requis.");
                model.addAttribute("livre", new Livre());
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            if (idGenre == null || idAuteur == null || nombreExemplaires == null) {
                model.addAttribute("message", "Erreur : Tous les champs numériques sont requis.");
                model.addAttribute("livre", new Livre());
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            if (nombreExemplaires < 0) {
                model.addAttribute("message", "Erreur : Le nombre d'exemplaires ne peut pas être négatif.");
                model.addAttribute("livre", new Livre());
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            // Validation de la catégorie
            Livre.CategorieAge categorieAge;
            try {
                categorieAge = Livre.CategorieAge.valueOf(categorie);
            } catch (IllegalArgumentException e) {
                model.addAttribute("message", "Erreur : Catégorie invalide. Valeurs possibles : Moins_de_18_ans, Plus_de_18_ans.");
                model.addAttribute("livre", new Livre());
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            // Vérifier l'existence du genre et de l'auteur
            if (!genreService.findById(idGenre).isPresent()) {
                model.addAttribute("message", "Erreur : Genre inexistant.");
                model.addAttribute("livre", new Livre());
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            if (!auteurService.findById(idAuteur).isPresent()) {
                model.addAttribute("message", "Erreur : Auteur inexistant.");
                model.addAttribute("livre", new Livre());
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }

            Livre livre = new Livre();
            livre.setTitre(titre.trim());
            livre.setIdGenre(idGenre);
            livre.setIdAuteur(idAuteur);
            livre.setCategorie(categorieAge); // Utiliser l'ENUM
            livre.setNombreExemplaires(nombreExemplaires);
            livreService.saveorUpdate(livre);
            return "redirect:/livres";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("livre", new Livre());
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("auteurs", auteurService.findAll());
            model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
            return "livre/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editLivre(@PathVariable Integer id, Model model) {
        Livre livre = livreService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livre introuvable"));
        model.addAttribute("livre", livre);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("auteurs", auteurService.findAll());
        model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
        return "livre/form";
    }

    @PostMapping("/update/{id}")
    public String updateLivre(@PathVariable Integer id, @RequestParam String titre, @RequestParam Integer idGenre,
                             @RequestParam Integer idAuteur, @RequestParam String categorie,
                             @RequestParam Integer nombreExemplaires, Model model) {
        try {
            // Validation des paramètres
            if (titre == null || titre.trim().isEmpty()) {
                model.addAttribute("message", "Erreur : Le titre est requis.");
                model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            if (idGenre == null || idAuteur == null || nombreExemplaires == null) {
                model.addAttribute("message", "Erreur : Tous les champs numériques sont requis.");
                model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            if (nombreExemplaires < 0) {
                model.addAttribute("message", "Erreur : Le nombre d'exemplaires ne peut pas être négatif.");
                model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            // Validation de la catégorie
            Livre.CategorieAge categorieAge;
            try {
                categorieAge = Livre.CategorieAge.valueOf(categorie);
            } catch (IllegalArgumentException e) {
                model.addAttribute("message", "Erreur : Catégorie invalide. Valeurs possibles : Moins_de_18_ans, Plus_de_18_ans.");
                model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            // Vérifier l'existence du genre et de l'auteur
            if (!genreService.findById(idGenre).isPresent()) {
                model.addAttribute("message", "Erreur : Genre inexistant.");
                model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }
            if (!auteurService.findById(idAuteur).isPresent()) {
                model.addAttribute("message", "Erreur : Auteur inexistant.");
                model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
                model.addAttribute("genres", genreService.findAll());
                model.addAttribute("auteurs", auteurService.findAll());
                model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
                return "livre/form";
            }

            Livre livre = livreService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Livre introuvable"));
            livre.setTitre(titre.trim());
            livre.setIdGenre(idGenre);
            livre.setIdAuteur(idAuteur);
            livre.setCategorie(categorieAge);
            livre.setNombreExemplaires(nombreExemplaires);
            livreService.saveorUpdate(livre);
            return "redirect:/livres";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("livre", livreService.findById(id).orElse(new Livre()));
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("auteurs", auteurService.findAll());
            model.addAttribute("categories", Arrays.asList(Livre.CategorieAge.values()));
            return "livre/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLivre(@PathVariable Integer id) {
        livreService.delete(id);
        return "redirect:/livres";
    }
}