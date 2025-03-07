package com.example.filmorate.controller;

import com.example.filmorate.service.GenreService;
import com.example.filmorate.storage.model.type.GENRE;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public Map<Integer, GENRE> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/{id}")
    public Optional<GENRE> getGenre(@PathVariable @Min(0) int id) {
        return genreService.getGenre(id);
    }
}
