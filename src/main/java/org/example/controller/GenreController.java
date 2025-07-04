package org.example.controller;

import org.example.entity.Genre;
import org.example.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Genres")
public class GenreController {

    private final GenreService GenreService;

    public GenreController(GenreService GenreService) {
        this.GenreService = GenreService;
    }

    @GetMapping
    public String listGenres(Model model) {
        List<Genre> Genres = GenreService.findAll();
        model.addAttribute("Genres", Genres);
        return "Genre/list";
    }

    @GetMapping("/new")
    public String newGenre(Model model) {
        model.addAttribute("Genre", new Genre());
        return "Genre/form";
    }

    @PostMapping("/save")
    public String saveGenre(@RequestParam String nom, Model model) {
        try {
            Genre Genre = new Genre();
            Genre.setNom(nom);
            GenreService.saveOrUpdate(Genre);
            return "redirect:/Genres";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("Genre", new Genre());
            return "Genre/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editGenre(@PathVariable Integer id, Model model) {
        Genre Genre = GenreService.findById(id).orElseThrow(() -> new IllegalArgumentException("Genre introuvable"));
        model.addAttribute("Genre", Genre);
        return "Genre/form";
    }

    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable Integer id, @RequestParam String nom, Model model) {
        try {
            Genre Genre = GenreService.findById(id).orElseThrow(() -> new IllegalArgumentException("Genre introuvable"));
            Genre.setNom(nom);
            GenreService.saveOrUpdate(Genre);
            return "redirect:/Genres";
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
            model.addAttribute("Genre", GenreService.findById(id).orElse(new Genre()));
            return "Genre/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Integer id) {
        GenreService.delete(id);
        return "redirect:/Genres";
    }
}
