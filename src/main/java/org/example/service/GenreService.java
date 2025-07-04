package org.example.service;

import org.example.entity.Genre;
import org.example.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository GenreRepository;

    public GenreService(GenreRepository GenreRepository) {
        this.GenreRepository = GenreRepository;
    }

    public List<Genre> findAll() {
        return GenreRepository.findAll();
    }

    public Optional<Genre> findById(Integer id) {
        return GenreRepository.findById(id);
    }

    public Genre saveOrUpdate(Genre genre) {
        return GenreRepository.save(genre);
    }

    public void delete(Integer id) {
        GenreRepository.deleteById(id);
    }
}
