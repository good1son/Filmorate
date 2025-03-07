package com.example.filmorate.controller;

import com.example.filmorate.storage.model.Film;
import com.example.filmorate.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/{id}")
    public Optional<Film> getFilm(@PathVariable int id) {
        return filmService.findFilmById(id);
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/popular")
    public Collection<Film> getPopular(@RequestParam(defaultValue = "3") int count) {
        return filmService.getPopular(count);
    }

    @PostMapping("/add")
    public void addFilm(@Valid @RequestBody Film film) {
        filmService.addFilm(film);
    }

    @PatchMapping("update/{id}")
    public void updateFilm(@PathVariable int id, @RequestBody Film film) {
        filmService.updateFilm(id, film);
    }

    @DeleteMapping("delete/{id}")
    public void deleteFilm(@PathVariable int id) {
        filmService.deleteFilmById(id);
    }

    /*
    // если отправить кол-во большее, чем кол-во фильмов,
    */



    /*

     */
}
