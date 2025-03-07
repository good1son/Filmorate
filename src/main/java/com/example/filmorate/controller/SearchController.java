package com.example.filmorate.controller;

import com.example.filmorate.service.SearchService;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public Collection<Film> searchFilm(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) GENRE genre,
                                       @RequestParam(required = false) String year,
                                       @RequestParam(required = false) MPA mpa) {
        return searchService.searchFilms(name, genre, year, mpa);
    }

}
